package com.example.submissionmade.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListMovieResponse(
    val page: Int = 0,
    val results: List<MovieResponse>,
    val totalPages: Int = 0,
    val totalResults: Int = 0,
)

data class MovieResponse(
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("title")
    val title: String = "",
    @field:SerializedName("release_date")
    val releaseDate: String = "",
    @field:SerializedName("vote_average")
    val score: Double = 0.0,
    @field:SerializedName("overview")
    val overview: String = "",
    @field:SerializedName("poster_path")
    val poster: String = "",
    @field:SerializedName("backdrop_path")
    val header: String = "",
)