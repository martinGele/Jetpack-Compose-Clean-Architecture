package com.demo.domain.repository

import com.demo.domain.entity.Photos
import kotlinx.coroutines.flow.Flow
import com.demo.domain.extension.Result

interface Repository {
     fun getItemList(): Flow<Result<List<Photos>>>
     fun getItem(id:Int):Flow<Photos>
}