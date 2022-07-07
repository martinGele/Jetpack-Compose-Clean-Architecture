package com.demo.presentation.mapper

import androidx.annotation.VisibleForTesting
import com.demo.presentation.model.Photos
import com.demo.domain.entity.Photos as DomainPhotos

class PhotosUIMapper {
    fun toUI(list: List<DomainPhotos>): List<Photos> {
        return list.map { toUI(it) }
    }

    @VisibleForTesting
    internal fun toUI(photos: DomainPhotos): Photos {
        return Photos(
            albumId = photos.albumId,
            id = photos.id,
            thumbnailUrl = photos.thumbnailUrl,
            title = photos.title,
            url = photos.url
        )
    }
}