package com.demo.data.source.remote

import com.demo.data.model.Photos

interface RemoteSource {
    suspend fun getItemList(): List<Photos>
}