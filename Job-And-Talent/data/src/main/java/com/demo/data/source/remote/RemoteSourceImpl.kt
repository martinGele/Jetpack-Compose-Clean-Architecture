package com.demo.data.source.remote

import com.demo.data.api.ApiService
import com.demo.data.model.Photos
import javax.inject.Inject

class RemoteSourceImpl @Inject constructor(
    private val apiService: ApiService
): RemoteSource {
    override suspend fun getItemList(): List<Photos> {
        return apiService.getAlbumList()
    }
}