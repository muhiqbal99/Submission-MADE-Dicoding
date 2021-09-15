package com.example.submissionmade.detail

import androidx.lifecycle.ViewModel
import com.example.submissionmade.core.domain.model.Items
import com.example.submissionmade.core.domain.usecase.ItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailItemViewModel @Inject constructor(private val itemUseCase: ItemUseCase) : ViewModel() {
    fun setFavoriteItem(items: Items, newState: Boolean) {
        itemUseCase.setFavorite(items, newState)
    }
}