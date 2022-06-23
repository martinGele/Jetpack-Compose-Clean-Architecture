package com.demo.data.source.local

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
class LocalSourceImplTest {

    private lateinit var localSourceImpl: LocalSourceImpl

    @RelaxedMockK
    private lateinit var daoPhotos: DaoPhotos

    private lateinit var photosList: List<Photos>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        localSourceImpl = LocalSourceImpl(daoPhotos)
        photosList = MockPhotosList().getPhotosList()
    }

    @Test
    fun testAddItemList() {
        runBlockingTest {
            coEvery { localSourceImpl.getItemList() } returns photosList

            localSourceImpl.addItemList(photosList)

            val data = localSourceImpl.getItemList()

            assertEquals(data, photosList)
        }
    }
}