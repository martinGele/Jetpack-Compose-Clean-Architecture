package com.demo.data.source.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.demo.data.model.MockPhotosLists
import com.demo.data.model.Photos
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class DaoPhotosTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: Database
    private lateinit var daoPhotos: DaoPhotos
    private lateinit var photosList: List<Photos>
    private lateinit var photo: Photos

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            Database::class.java
        ).allowMainThreadQueries().build()

        daoPhotos = database.getPhotosDao()
        photosList = MockPhotosLists().getPhotosList()
        photo = MockPhotosLists().getPhotos()
    }

    @After
    @Throws(IOException::class)
    fun teardown() {
        database.close()
    }

    @Test
    @Throws(Exception::class)
    fun testAddPhotosList() = runBlockingTest {
        daoPhotos.addPhotosList(photosList)

        val data = daoPhotos.getPhotosList()

        assertThat(data).contains(photosList[0])
    }


    @Test
    @Throws(Exception::class)
    fun testGetSinglePhoto() = runBlockingTest {
        daoPhotos.addPhotosList(photosList)

        val data = daoPhotos.getSinglePhoto(1)

        assertThat(data).isEqualTo(photo)
    }

    @Test
    @Throws(Exception::class)
    fun testNoPhoto() = runBlockingTest {
        daoPhotos.addPhotosList(photosList)

        val data = daoPhotos.getSinglePhoto(2)

        assertThat(data).isEqualTo(null)
    }

}