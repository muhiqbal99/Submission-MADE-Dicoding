package com.example.submissionmade.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_items")
data class ItemsEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "title")
    var title: String = "",

    @ColumnInfo(name = "releaseDate")
    var releaseDate: String = "",

    @ColumnInfo(name = "score")
    var score: Double = 0.0,

    @ColumnInfo(name = "overview")
    var overview: String = "",

    @ColumnInfo(name = "poster")
    var poster: String = "",

    @ColumnInfo(name = "header")
    var header: String = "",

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false,

    @ColumnInfo(name = "type")
    var type: String = "",
)