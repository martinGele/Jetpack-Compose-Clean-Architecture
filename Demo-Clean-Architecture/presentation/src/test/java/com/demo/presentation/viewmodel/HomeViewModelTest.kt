package com.demo.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.demo.domain.extension.Result
import com.demo.domain.usecase.ItemUseCase
import com.demo.domain.entity.Photos as DomainPhotos
import com.demo.presentation.mapper.PhotosUIMapper
import com.demo.presentation.model.DomainMockPhotosList
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class HomeViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var viewModel: HomeViewModel


    private lateinit var photosList: List<DomainPhotos>

    @RelaxedMockK
    private lateinit var getUseCase: ItemUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = HomeViewModel(getUseCase)
        photosList = DomainMockPhotosList().getPhotosList()
    }

    @Test
    fun loadingState() {
        testCoroutineRule.runBlockingTest {
            coEvery { getUseCase.invoke() } returns flow { emit(Result.Loading) }

            viewModel.getPhotosList()

            Truth.assertThat(viewModel.loadingState.value).isEqualTo(true)
            assertEquals(viewModel.state.value, PhotosUIMapper().toUI(emptyList()))
        }
    }

    @Test
    fun successState() {
        testCoroutineRule.runBlockingTest {
            coEvery { getUseCase.invoke() } returns flow {
                emit(Result.Success(photosList))
            }
            viewModel.getPhotosList()

            assertEquals(viewModel.state.value, PhotosUIMapper().toUI(photosList))
            Truth.assertThat(viewModel.loadingState.value).isEqualTo(false)
        }
    }

    @Test
    fun errorState() {
        testCoroutineRule.runBlockingTest {
            coEvery { getUseCase.invoke() } returns flow { emit(Result.Error("Error")) }

            viewModel.getPhotosList()

            Truth.assertThat(viewModel.loadingState.value).isEqualTo(false)
            assertEquals(viewModel.errorState.value, "Error")
        }
    }
}