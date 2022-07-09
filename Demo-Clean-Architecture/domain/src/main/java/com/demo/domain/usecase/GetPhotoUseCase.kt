package com.demo.domain.usecase

import com.demo.domain.entity.Photos
import com.demo.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPhotoUseCase @Inject constructor(
    private val repository: Repository
) {
     operator fun invoke(id: Int): Flow<Photos> =
        repository.getItem(id)
}