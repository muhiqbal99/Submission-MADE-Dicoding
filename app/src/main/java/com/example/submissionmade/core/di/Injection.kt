package com.example.submissionmade.core.di

import android.content.Context
import com.example.submissionmade.core.data.ItemsRepository
import com.example.submissionmade.core.data.source.local.LocalDataSource
import com.example.submissionmade.core.data.source.local.room.ItemsDatabase
import com.example.submissionmade.core.data.source.remote.RemoteDataSource
import com.example.submissionmade.core.data.source.remote.network.ApiConfig
import com.example.submissionmade.core.domain.repository.IItemRepository
import com.example.submissionmade.core.domain.usecase.ItemInteractor
import com.example.submissionmade.core.domain.usecase.ItemUseCase
import com.example.submissionmade.core.utils.AppExecutors

object Injection {

    private fun provideRepository(context: Context): IItemRepository {

        val database = ItemsDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.itemDao())
        val appExecutors = AppExecutors()

        return ItemsRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideItemUseCase(context: Context): ItemUseCase {
        val repository = provideRepository(context)
        return ItemInteractor(repository)
    }
}