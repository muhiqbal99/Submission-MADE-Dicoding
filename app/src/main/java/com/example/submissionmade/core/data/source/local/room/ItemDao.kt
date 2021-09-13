package com.example.submissionmade.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.submissionmade.core.data.source.local.entity.ItemsEntity

@Dao
interface ItemDao {

    @Query("SELECT * FROM tb_items where type = :type")
    fun getItems(type: String): LiveData<List<ItemsEntity>>

    @Query("SELECT * FROM tb_items where id = :id")
    fun getMovieById(id: Int): LiveData<ItemsEntity>

    @Query("SELECT * FROM tb_items where id = :id")
    fun getTvShowById(id: Int): LiveData<ItemsEntity>

    @Query("SELECT * FROM tb_items where isFavorite = 1")
    fun getFavoriteList(): LiveData<List<ItemsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(items: List<ItemsEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetail(items: List<ItemsEntity>)

    @Update
    fun updateItem(item: ItemsEntity)
}