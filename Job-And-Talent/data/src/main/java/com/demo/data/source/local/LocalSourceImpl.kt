package com.demo.data.source.local

import com.demo.data.model.Photos
import javax.inject.Inject

class LocalSourceImpl @Inject constructor(
    private val daoPhotos: DaoPhotos
): LocalSource {
    override suspend fun getItemList(): List<Photos> {
        return daoPhotos.getPhotosList()
    }

    override suspend fun addItemList(items: List<Photos>) {
        daoPhotos.addPhotosList(items)
    }
}