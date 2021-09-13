package com.example.submissionmade.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Items(
    val id: Int,
    val title: String,
    val releaseDate: String,
    val score: Double,
    val overview: String,
    val poster: String,
    val header: String,
    val type: String,
    val isFavorite: Boolean,
) : Parcelable