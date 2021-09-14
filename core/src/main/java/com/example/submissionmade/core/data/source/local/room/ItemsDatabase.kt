package com.example.submissionmade.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.submissionmade.core.data.source.local.entity.ItemsEntity

@Database(entities = [ItemsEntity::class],
    version = 1,
    exportSchema = false)
abstract class ItemsDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemsDao

}