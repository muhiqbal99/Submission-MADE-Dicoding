package com.example.submissionmade.core.data.source.local

import com.example.submissionmade.core.data.source.local.entity.ItemsEntity
import com.example.submissionmade.core.data.source.local.room.ItemsDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource private constructor(private val itemsDao: ItemsDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(itemsDao: ItemsDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(itemsDao)
    }

    fun getMovieItem(): Flow<List<ItemsEntity>> = itemsDao.getItems("movie")

    fun getTvShowItem(): Flow<List<ItemsEntity>> = itemsDao.getItems("tvshow")

    fun getFavoriteItemList(): Flow<List<ItemsEntity>> = itemsDao.getFavoriteList()

    suspend fun insertItems(items: List<ItemsEntity>) = itemsDao.insertItem(items)

    fun setFavorite(items: ItemsEntity, newState: Boolean) {
        items.isFavorite = newState
        itemsDao.updateItem(items)
    }
}