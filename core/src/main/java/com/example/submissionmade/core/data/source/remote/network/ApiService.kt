package com.example.submissionmade.core.data.source.remote.network

import com.example.submissionmade.core.BuildConfig
import com.example.submissionmade.core.data.source.remote.response.ListMovieResponse
import com.example.submissionmade.core.data.source.remote.response.ListTvShowResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie")
    suspend fun getMovie(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ): ListMovieResponse

    @GET("discover/tv")
    suspend fun getTvShow(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ): ListTvShowResponse

}