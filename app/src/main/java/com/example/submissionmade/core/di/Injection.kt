package com.example.submissionmade.core.di

import android.content.Context
import com.example.submissionmade.core.data.ItemRepository
import com.example.submissionmade.core.data.source.local.LocalDataSource
import com.example.submissionmade.core.data.source.local.room.ItemDatabase
import com.example.submissionmade.core.data.source.remote.RemoteDataSource
import com.example.submissionmade.core.domain.repository.IItemRepository
import com.example.submissionmade.core.domain.usecase.ItemInteractor
import com.example.submissionmade.core.domain.usecase.ItemUseCase
import com.example.submissionmade.core.utils.AppExecutors

object Injection {

    fun provideRepository(context: Context): IItemRepository {

        val database = ItemDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()

        return ItemRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideItemUseCase(context: Context): ItemUseCase {
        val repository = provideRepository(context)
        return ItemInteractor(repository)
    }
}