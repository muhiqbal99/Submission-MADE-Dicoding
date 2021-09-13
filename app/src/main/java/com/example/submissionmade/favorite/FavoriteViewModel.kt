package com.example.submissionmade.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.submissionmade.core.domain.model.Items
import com.example.submissionmade.core.domain.usecase.ItemUseCase

class FavoriteViewModel(private val itemUseCase: ItemUseCase) : ViewModel() {

    fun getFavoriteItem(): LiveData<List<Items>> = itemUseCase.getFavoriteItem().asLiveData()

}