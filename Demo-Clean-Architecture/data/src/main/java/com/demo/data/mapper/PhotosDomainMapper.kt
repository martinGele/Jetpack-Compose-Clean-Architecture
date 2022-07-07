package com.demo.data.mapper

import androidx.annotation.VisibleForTesting
import com.demo.domain.entity.Photos as DomainPhotos
import com.demo.data.model.Photos as DataPhotos

internal class PhotosDomainMapper {

    fun toDomain(list: List<DataPhotos>): List<DomainPhotos> {
        return list.map { toDomain(it) }
    }

    @VisibleForTesting
    internal fun toDomain(photos: DataPhotos): DomainPhotos {
        return DomainPhotos(
            albumId = photos.albumId,
            id = photos.id,
            thumbnailUrl = photos.thumbnailUrl,
            title = photos.title,
            url = photos.url
        )
    }

}