package com.example.submissionmade.detail

import androidx.lifecycle.ViewModel
import com.example.submissionmade.core.domain.model.Items
import com.example.submissionmade.core.domain.usecase.ItemUseCase

class DetailItemViewModel(private val itemUseCase: ItemUseCase) : ViewModel() {

    fun setFavoriteItem(items: Items, newState: Boolean) {
        itemUseCase.setFavorite(items, newState)
    }
}