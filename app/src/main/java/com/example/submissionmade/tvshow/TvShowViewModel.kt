package com.example.submissionmade.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.submissionmade.core.data.Resource
import com.example.submissionmade.core.domain.model.Item
import com.example.submissionmade.core.domain.usecase.ItemUseCase

class TvShowViewModel(private val itemUseCase: ItemUseCase) : ViewModel() {

    fun getTvShow(): LiveData<Resource<List<Item>>> = itemUseCase.getTvShowItem()
}