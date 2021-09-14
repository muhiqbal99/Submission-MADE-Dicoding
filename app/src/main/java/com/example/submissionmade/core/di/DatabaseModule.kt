package com.example.submissionmade.core.di

import android.content.Context
import androidx.room.Room
import com.example.submissionmade.core.data.source.local.room.ItemsDao
import com.example.submissionmade.core.data.source.local.room.ItemsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): ItemsDatabase = Room.databaseBuilder(
        context,
        ItemsDatabase::class.java, "tb_items.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideTourismDao(database: ItemsDatabase): ItemsDao = database.itemDao()
}