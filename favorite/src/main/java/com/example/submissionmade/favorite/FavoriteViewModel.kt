package com.example.submissionmade.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.submissionmade.core.domain.usecase.ItemUseCase

class FavoriteViewModel(private val itemUseCase: ItemUseCase) : ViewModel() {

    val getFavoriteItem = itemUseCase.getFavoriteItem().asLiveData()
}