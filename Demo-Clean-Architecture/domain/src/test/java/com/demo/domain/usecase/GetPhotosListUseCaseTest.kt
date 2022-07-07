package com.demo.domain.usecase

import com.demo.domain.entity.Photos
import com.demo.domain.entity.MockPhotosList
import com.demo.domain.repository.Repository
import com.demo.domain.extension.Result
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetPhotosListUseCaseTest {
    @RelaxedMockK
    lateinit var repo: Repository

    private lateinit var useCase: GetPhotosUseCase
    private lateinit var photosList: List<Photos>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = GetPhotosUseCase(repo)
        photosList = MockPhotosList().getPhotosList()
    }

    @OptIn(InternalCoroutinesApi::class)
    @Test
    fun `flow is success result photos`() {
        runBlocking {
            coEvery { repo.getItemList() } returns flow { emit(Result.Success(photosList)) }

            val data =  useCase.invoke()

            coVerify { useCase.invoke() }

            data.collectLatest {
                Truth.assertThat(it is Result.Success).isTrue()
            }
        }
    }

    @Test
    fun `flow is loading result photos`() {
        runBlocking {
            coEvery { repo.getItemList() } returns flow { emit(Result.Loading) }

            val data =  useCase.invoke()

            coVerify { useCase.invoke() }

            data.collectLatest { result ->
                Truth.assertThat(result is Result.Loading).isTrue()
            }
        }
    }

    @Test
    fun `flow is error result photos`() {
        runBlocking {
            coEvery { repo.getItemList() } returns flow { emit(Result.Error("Error")) }

            val data =  useCase.invoke()

            coVerify { useCase.invoke() }

            data.collectLatest { result ->
                Truth.assertThat(result is Result.Error).isTrue()
            }
        }
    }
}