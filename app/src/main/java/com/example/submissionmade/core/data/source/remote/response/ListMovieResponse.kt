package com.example.submissionmade.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListMovieResponse(
    val page: Int = 0,
    val results: List<MovieResponse>,
    val totalPages: Int = 0,
    val totalResults: Int = 0,
)

data class MovieResponse(
    val id: Int,
    val title: String = "",
    @field:SerializedName("release_date")
    val releaseDate: String = "",
    @field:SerializedName("vote_average")
    val score: Double = 0.0,
    val overview: String = "",
    @field:SerializedName("poster_path")
    val poster: String = "",
    @field:SerializedName("backdrop_path")
    val header: String = "",
)