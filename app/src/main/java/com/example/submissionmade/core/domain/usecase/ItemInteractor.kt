package com.example.submissionmade.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.submissionmade.core.data.Resource
import com.example.submissionmade.core.domain.model.Item
import com.example.submissionmade.core.domain.repository.IItemRepository

class ItemInteractor(private val itemRepository: IItemRepository) : ItemUseCase {

    override fun getMovieItem(): LiveData<Resource<List<Item>>> = itemRepository.getMovieItem()

    override fun getTvShowItem(): LiveData<Resource<List<Item>>> = itemRepository.getTvShowItem()

    override fun getFavoriteItem(): LiveData<List<Item>> = itemRepository.getFavoriteItem()

    override fun setFavorite(item: Item, state: Boolean) = itemRepository.setFavorite(item, state)

}