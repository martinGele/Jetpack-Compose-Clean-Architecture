package com.demo.domain.repository

import com.demo.domain.entity.Photos
import kotlinx.coroutines.flow.Flow
import com.demo.domain.extension.Result

interface Repository {
    suspend fun getItemList(): Flow<Result<List<Photos>>>
    suspend fun getItem(id:Int):Photos
}