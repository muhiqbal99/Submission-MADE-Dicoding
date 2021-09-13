package com.example.submissionmade.detail

import androidx.lifecycle.ViewModel
import com.example.submissionmade.core.domain.model.Item
import com.example.submissionmade.core.domain.usecase.ItemUseCase

class DetailItemViewModel(private val itemUseCase: ItemUseCase) : ViewModel() {

    fun setFavoriteItem(item: Item, newState: Boolean) {
        itemUseCase.setFavorite(item, newState)
    }
}