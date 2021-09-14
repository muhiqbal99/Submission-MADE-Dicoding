package com.example.submissionmade.di

import com.example.submissionmade.core.domain.usecase.ItemInteractor
import com.example.submissionmade.core.domain.usecase.ItemUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideItemUseCase(itemInteractor: ItemInteractor): ItemUseCase
}