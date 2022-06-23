package com.demo.domain.usecase

import com.demo.domain.entity.Photos
import com.demo.domain.repository.Repository
import com.demo.domain.extension.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ItemUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): Flow<Result<List<Photos>>> =
        repository.getItemList()
}