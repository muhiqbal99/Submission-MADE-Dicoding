package com.example.submissionmade.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListTvShowResponse(
    val page: Int = 0,
    val results: List<TvShowResponse>,
    val totalPages: Int = 0,
    val totalResults: Int = 0,
)

data class TvShowResponse(
    @field:SerializedName("id")
    val id: Int = 0,
    @field:SerializedName("name")
    val title: String = "",
    @field:SerializedName("first_air_date")
    val releaseDate: String = "",
    @field:SerializedName("vote_average")
    val score: Double = 0.0,
    @field:SerializedName("overview")
    val overview: String = "",
    @field:SerializedName("poster_path")
    val poster: String = "",
    @field:SerializedName("backdrop_path")
    val header: String?,
)