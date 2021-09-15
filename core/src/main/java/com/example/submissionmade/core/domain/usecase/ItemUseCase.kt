package com.example.submissionmade.core.domain.usecase

import com.example.submissionmade.core.data.Resource
import com.example.submissionmade.core.domain.model.Items
import kotlinx.coroutines.flow.Flow

interface ItemUseCase {

    fun getMovieItem(): Flow<Resource<List<Items>>>

    fun getTvShowItem(): Flow<Resource<List<Items>>>

    fun getFavoriteItem(): Flow<List<Items>>

    fun setFavorite(items: Items, state: Boolean)
}