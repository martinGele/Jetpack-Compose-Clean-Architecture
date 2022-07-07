package com.demo.data.source.remote

import com.demo.data.api.ApiService
import com.demo.data.model.Photos
import com.demo.data.model.MockPhotosList
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RemoteSourceImplTest {

    private lateinit var remoteDataSourceImpl: RemoteSourceImpl

    @RelaxedMockK
    private lateinit var apiService: ApiService

    private lateinit var photosList: List<Photos>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        remoteDataSourceImpl = RemoteSourceImpl(apiService)
        photosList = MockPhotosList().getPhotosList()
    }

    @Test
    fun getItemListDataSource() {
        runBlockingTest {
            coEvery { remoteDataSourceImpl.getItemList() } returns photosList

            val data = remoteDataSourceImpl.getItemList()

            assertEquals(data, photosList)
        }
    }
}