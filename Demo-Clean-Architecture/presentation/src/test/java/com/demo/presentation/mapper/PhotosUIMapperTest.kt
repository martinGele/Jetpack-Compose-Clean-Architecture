package com.demo.presentation.mapper

import com.demo.presentation.model.Photos
import com.demo.presentation.model.DomainMockPhotosList
import com.demo.presentation.model.MockPhotosList
import com.demo.domain.entity.Photos as DomainPhotos
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class PhotosUIMapperTest {

    @RelaxedMockK
    private lateinit var mapper: PhotosUIMapper

    private lateinit var photosList: List<Photos>
    private lateinit var domainPhotosList: List<DomainPhotos>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        photosList = MockPhotosList().getPhotosList()
        domainPhotosList = DomainMockPhotosList().getPhotosList()
    }

    @Test
    fun toDomainList() {
        every { mapper.toUI(domainPhotosList) } returns photosList

        val data = mapper.toUI(domainPhotosList)

        assertEquals(data, photosList)
    }

    @Test
    fun toDomainItem() {
        every { mapper.toUI(domainPhotosList[0]) } returns photosList[0]

        val data = mapper.toUI(domainPhotosList[0])

        assertEquals(data, photosList[0])
    }
}