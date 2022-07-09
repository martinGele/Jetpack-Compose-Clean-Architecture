package com.demo.domain.usecase

import com.demo.domain.entity.MockPhotosList
import com.demo.domain.entity.Photos
import com.demo.domain.repository.Repository
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetPhotoUseCaseTest {

    @RelaxedMockK
    lateinit var repo: Repository

    private lateinit var useCase: GetPhotoUseCase
    private lateinit var photo: Photos

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = GetPhotoUseCase(repo)
        photo = MockPhotosList().getPhotos()
    }

    @Test
    fun getItem() {
        runBlocking {
            coEvery { repo.getItem(1) } returns flow { photo }

            val data = useCase.invoke(1)

            coVerify { useCase.invoke(1) }

            data.collectLatest {
                Truth.assertThat(it).isEqualTo(photo)
            }
        }
    }
}