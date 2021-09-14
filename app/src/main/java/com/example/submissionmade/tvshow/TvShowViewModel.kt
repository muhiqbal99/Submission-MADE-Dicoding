package com.example.submissionmade.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.submissionmade.core.data.Resource
import com.example.submissionmade.core.domain.model.Items
import com.example.submissionmade.core.domain.usecase.ItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel @Inject constructor(private val itemUseCase: ItemUseCase) : ViewModel() {

    fun getTvShow(): LiveData<Resource<List<Items>>> = itemUseCase.getTvShowItem().asLiveData()
}