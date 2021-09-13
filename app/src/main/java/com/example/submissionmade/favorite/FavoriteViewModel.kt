package com.example.submissionmade.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.submissionmade.core.domain.model.Item
import com.example.submissionmade.core.domain.usecase.ItemUseCase

class FavoriteViewModel(private val itemUseCase: ItemUseCase) : ViewModel() {

    fun getFavoriteItem(): LiveData<List<Item>> = itemUseCase.getFavoriteItem()

}