package com.demo.data.api

import com.demo.data.model.Photos
import retrofit2.http.GET

interface ApiService {
    @GET("photos")
    suspend fun getAlbumList(): List<Photos>
}