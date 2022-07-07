package com.demo.domain.usecase

import com.demo.domain.entity.Photos
import com.demo.domain.repository.Repository
import javax.inject.Inject

class GetPhotoUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(id: Int): Photos =
        repository.getItem(id)
}