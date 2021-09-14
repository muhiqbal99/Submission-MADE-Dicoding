package com.example.submissionmade.core.di

import com.example.submissionmade.core.data.ItemsRepository
import com.example.submissionmade.core.domain.repository.IItemRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(itemsRepository: ItemsRepository): IItemRepository

}