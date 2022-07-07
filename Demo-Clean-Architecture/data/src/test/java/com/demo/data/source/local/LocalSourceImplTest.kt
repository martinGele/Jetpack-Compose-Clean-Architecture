package com.demo.data.source.local

import com.demo.data.model.MockPhotosList
import com.demo.data.model.Photos
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LocalSourceImplTest {

    private lateinit var localSourceImpl: LocalSourceImpl

    @RelaxedMockK
    private lateinit var daoPhotos: DaoPhotos

    private lateinit var photosList: List<Photos>

    private lateinit var photo: Photos

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        localSourceImpl = LocalSourceImpl(daoPhotos)
        photosList = MockPhotosList().getPhotosList()
        photo = MockPhotosList().getPhotos()
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

    @Test
    fun testGetItem() {
        runBlockingTest {
            coEvery { localSourceImpl.getSingleItem(1) } returns photo

            localSourceImpl.addItemList(photosList)

            val data = localSourceImpl.getSingleItem(1)

            assertEquals(data, photo)
        }
    }

    @Test
    fun testGetItemList() {
        runBlockingTest {
            coEvery { localSourceImpl.getItemList() } returns photosList

            val data = localSourceImpl.getItemList()

            assertEquals(data, photosList)
        }
    }
}