package com.example.submissionmade.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.submissionmade.core.data.source.local.entity.ItemsEntity

@Database(entities = [ItemsEntity::class], version = 1, exportSchema = false)
abstract class ItemsDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemsDao

    companion object {

        @Volatile
        private var INSTANCE: ItemsDatabase? = null

        fun getInstance(context: Context): ItemsDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    ItemsDatabase::class.java,
                    "tb_items.db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}