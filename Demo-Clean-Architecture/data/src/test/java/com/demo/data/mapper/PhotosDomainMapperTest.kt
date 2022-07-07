package com.demo.data.mapper

import com.demo.data.model.Photos
import com.demo.domain.entity.Photos as DomainPhotos
import com.demo.data.model.MockPhotosList
import com.demo.data.model.DomainMockPhotosList
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class PhotosDomainMapperTest {

    @RelaxedMockK
    private lateinit var mapper: PhotosDomainMapper

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
        every { mapper.toDomain(photosList) } returns domainPhotosList

        val data = mapper.toDomain(photosList)

        assertEquals(data, domainPhotosList)
    }

    @Test
    fun toDomainItem() {
        every { mapper.toDomain(photosList[0]) } returns domainPhotosList[0]

        val data = mapper.toDomain(photosList[0])

        assertEquals(data, domainPhotosList[0])
    }
}