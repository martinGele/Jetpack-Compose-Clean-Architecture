package com.demo.presentation.di

import com.demo.domain.repository.Repository
import com.demo.domain.usecase.ItemUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PresentationModule {

    @Provides
    fun provideItemUseCase(repository: Repository): ItemUseCase {
        return ItemUseCase(repository)
    }
}