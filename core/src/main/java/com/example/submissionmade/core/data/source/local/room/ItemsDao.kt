package com.example.submissionmade.core.data.source.local.room

import androidx.room.*
import com.example.submissionmade.core.data.source.local.entity.ItemsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemsDao {

    @Query("SELECT * FROM tb_items where type = :type")
    fun getItems(type: String): Flow<List<ItemsEntity>>

    @Query("SELECT * FROM tb_items where isFavorite = 1")
    fun getFavoriteList(): Flow<List<ItemsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(items: List<ItemsEntity>)

    @Update
    fun updateItem(item: ItemsEntity)
}