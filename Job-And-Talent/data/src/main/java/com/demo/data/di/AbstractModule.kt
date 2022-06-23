package com.demo.data.di

import com.demo.data.repository.RepositoryImpl
import com.demo.data.source.local.LocalSource
import com.demo.data.source.local.LocalSourceImpl
import com.demo.data.source.remote.RemoteSource
import com.demo.data.source.remote.RemoteSourceImpl
import com.demo.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AbstractModule {

    @Binds
    abstract fun bindRepository(repositoryImpl: RepositoryImpl): Repository

    @Binds
    abstract fun bindLocalSource(localSourceImpl: LocalSourceImpl): LocalSource

    @Binds
    abstract fun bindRemoteSource(remoteSourceImpl: RemoteSourceImpl): RemoteSource
}