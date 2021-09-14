package com.example.submissionmade.core.domain.usecase

import com.example.submissionmade.core.data.Resource
import com.example.submissionmade.core.domain.model.Items
import com.example.submissionmade.core.domain.repository.IItemRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ItemInteractor @Inject constructor(private val itemRepository: IItemRepository) :
    ItemUseCase {

    override fun getMovieItem(): Flow<Resource<List<Items>>> =
        itemRepository.getMovieItem()

    override fun getTvShowItem(): Flow<Resource<List<Items>>> =
        itemRepository.getTvShowItem()

    override fun getFavoriteItem(): Flow<List<Items>> = itemRepository.getFavoriteItem()

    override fun setFavorite(items: Items, state: Boolean) =
        itemRepository.setFavorite(items, state)

}