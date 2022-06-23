package com.demo.data.repository

import com.demo.data.mapper.PhotosDomainMapper
import com.demo.data.source.local.LocalSource
import com.demo.data.source.remote.RemoteSource
import com.demo.domain.extension.repoFlow
import com.demo.domain.repository.Repository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val remoteSource: RemoteSource,
    private val localSource: LocalSource
): Repository {
    override suspend fun getItemList() = repoFlow {
        try {
            val data = remoteSource.getItemList()
            if (data.isNotEmpty()) {
                localSource.addItemList(data)
            }
            PhotosDomainMapper().toDomain(localSource.getItemList())
        } catch (e: Exception) {
            PhotosDomainMapper().toDomain(localSource.getItemList())
        }
    }.flowOn(dispatcher)

}