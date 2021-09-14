package com.example.submissionmade.di

import com.example.submissionmade.core.domain.usecase.ItemUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDepedencies {

    fun itemUseCase(): ItemUseCase
}

