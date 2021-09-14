package com.example.submissionmade.core.data.source.local

import com.example.submissionmade.core.data.source.local.entity.ItemsEntity
import com.example.submissionmade.core.data.source.local.room.ItemsDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val itemsDao: ItemsDao) {

    fun getMovieItem(): Flow<List<ItemsEntity>> = itemsDao.getItems("movie")

    fun getTvShowItem(): Flow<List<ItemsEntity>> = itemsDao.getItems("tvshow")

    fun getFavoriteItemList(): Flow<List<ItemsEntity>> = itemsDao.getFavoriteList()

    suspend fun insertItems(items: List<ItemsEntity>) = itemsDao.insertItem(items)

    fun setFavorite(items: ItemsEntity, newState: Boolean) {
        items.isFavorite = newState
        itemsDao.updateItem(items)
    }
}