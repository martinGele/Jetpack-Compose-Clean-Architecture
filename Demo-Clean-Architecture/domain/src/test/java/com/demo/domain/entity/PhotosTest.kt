package com.demo.domain.entity

import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class PhotosTest {

    private lateinit var photos: Photos

    @Before
    fun setUp() {
        photos = MockPhotosList().getPhotos()
    }


    @Test
    fun getImage_link() {
        val data = photos.copy(thumbnailUrl = "https://via.placeholder.com/150/92c952")
        assertEquals(photos.thumbnailUrl, data.thumbnailUrl)
    }

    @Test
    fun getAlbum_Id() {
        val data = photos.copy(albumId = 1)
        assertEquals(photos.albumId, data.albumId)
    }

    @Test
    fun get_Id() {
        val data = photos.copy(id = 1)
        assertEquals(photos.id, data.id)
    }
    @Test
    fun get_Title() {
        val data = photos.copy(title = "accusamus= beatae ad facilis cum similique qui sunt")
        assertEquals(photos.title, data.title)
    }

    @Test
    fun get_URL() {
        val data = photos.copy(url = "https://via.placeholder.com/600/92c952")
        assertEquals(photos.url, data.url)
    }
}