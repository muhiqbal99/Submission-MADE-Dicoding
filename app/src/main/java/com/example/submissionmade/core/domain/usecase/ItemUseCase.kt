package com.example.submissionmade.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.submissionmade.core.data.Resource
import com.example.submissionmade.core.domain.model.Item

interface ItemUseCase {

    fun getMovieItem(): LiveData<Resource<List<Item>>>

    fun getTvShowItem(): LiveData<Resource<List<Item>>>

    fun getFavoriteItem(): LiveData<List<Item>>

    fun setFavorite(item: Item, state: Boolean)
}