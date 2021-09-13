package com.example.submissionmade.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListTvShowResponse(
    val page: Int = 0,
    val results: List<TvShowResponse>,
    val totalPages: Int = 0,
    val totalResults: Int = 0,
)

data class TvShowResponse(
    val id: Int = 0,
    @field:SerializedName("name")
    val title: String = "",
    @field:SerializedName("first_air_date")
    val releaseDate: String = "",
    @field:SerializedName("vote_average")
    val score: Double = 0.0,
    val overview: String = "",
    @field:SerializedName("poster_path")
    val poster: String = "",
    val isFavorite: Boolean = false,
)