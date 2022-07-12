package com.demo.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.demo.domain.entity.Photos
import com.demo.domain.usecase.GetPhotoUseCase
import com.demo.presentation.mapper.PhotosUIMapper
import com.demo.presentation.model.DomainMockPhotosList
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule


@ExperimentalCoroutinesApi
class DetailViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var viewModel: DetailViewModel


    private lateinit var photo: Photos

    @RelaxedMockK
    private lateinit var getUseCase: GetPhotoUseCase


    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = DetailViewModel(getUseCase)
        photo = DomainMockPhotosList().getPhotos()
    }

    @Test
    fun getSingleItem() {
        testCoroutineRule.runBlockingTest {
            coEvery { getUseCase.invoke(1) } returns flow { emit(photo) }

            viewModel.getPhoto(2)

            Assert.assertEquals(viewModel.state.value, PhotosUIMapper().toUI(photo))

        }
    }
}