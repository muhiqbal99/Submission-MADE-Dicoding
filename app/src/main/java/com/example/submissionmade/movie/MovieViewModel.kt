package com.example.submissionmade.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.submissionmade.core.data.Resource
import com.example.submissionmade.core.domain.model.Items
import com.example.submissionmade.core.domain.usecase.ItemUseCase

class MovieViewModel(private val itemUseCase: ItemUseCase) : ViewModel() {

    fun getMovies(): LiveData<Resource<List<Items>>> = itemUseCase.getMovieItem().asLiveData()
}