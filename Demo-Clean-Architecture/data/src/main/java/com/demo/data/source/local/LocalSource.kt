package com.demo.data.source.local

import com.demo.data.model.Photos

interface LocalSource {
    suspend fun getItemList(): List<Photos>
    suspend fun addItemList(items: List<Photos>)
    suspend fun getSingleItem(id:Int):Photos
}