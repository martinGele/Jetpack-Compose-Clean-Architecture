package com.demo.data.di

import android.content.Context
import androidx.room.Room
import com.demo.data.api.ApiService
import com.demo.data.api.RetrofitBuilder
import com.demo.data.repository.RepositoryImpl
import com.demo.data.source.local.DaoPhotos
import com.demo.data.source.local.Database
import com.demo.data.source.local.LocalSource
import com.demo.data.source.local.LocalSourceImpl
import com.demo.data.source.remote.RemoteSource
import com.demo.data.source.remote.RemoteSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    fun provideApiService(): ApiService =
        RetrofitBuilder.getRetrofit().create(ApiService::class.java)

    @Provides
    fun provideIoDispatcher() = Dispatchers.IO

    @Provides
    fun provideLocalSource(daoPhotos: DaoPhotos): LocalSourceImpl {
        return LocalSourceImpl(daoPhotos)
    }

    @Provides
    fun provideRemoteSource(apiService: ApiService): RemoteSourceImpl {
        return RemoteSourceImpl(apiService)
    }

    @Provides
    fun provideRepository(
        ioDispatcher: CoroutineDispatcher,
        localSource: LocalSource,
        remoteSource: RemoteSource
    ): RepositoryImpl {
        return RepositoryImpl(
            dispatcher = ioDispatcher,
            localSource = localSource,
            remoteSource = remoteSource
        )
    }

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java,
            Database.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideDao(database: Database): DaoPhotos {
        return database.getPhotosDao()
    }
}