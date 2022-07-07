package com.demo.data.repository

import com.demo.domain.extension.Result
import com.demo.data.model.Photos
import com.demo.data.model.MockPhotosList
import com.demo.data.source.local.LocalSource
import com.demo.data.source.remote.RemoteSource
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest

import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RepositoryImplTest {

    private val coroutineDispatcher = TestCoroutineDispatcher()

    private lateinit var repositoryImpl: RepositoryImpl

    @RelaxedMockK
    private lateinit var remoteDataSource: RemoteSource

    @RelaxedMockK
    private lateinit var localeDataSource: LocalSource

    private lateinit var photosList: List<Photos>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repositoryImpl = RepositoryImpl(coroutineDispatcher, remoteDataSource, localeDataSource)
        photosList = MockPhotosList().getPhotosList()
    }

    @Test
    fun testGetItemListOnSuccess() {
        coroutineDispatcher.runBlockingTest {
            coEvery { remoteDataSource.getItemList() } returns photosList

            val repo = repositoryImpl.getItemList()

            repo.collectLatest {
                if(it is Result.Success) {
                    Truth.assertThat(it.data).isEqualTo(MockPhotosList().getPhotosList())
                }
            }
        }
    }

    @Test
    fun testGetEmptyListOnError() {
        coroutineDispatcher.runBlockingTest {
            coEvery { remoteDataSource.getItemList() } returns emptyList()

            val repo = repositoryImpl.getItemList()

            repo.collectLatest {
                if(it is Result.Error) {
                    Truth.assertThat("Unknown error").isEqualTo(null)
                }
            }
        }
    }
}