package com.example.submissionmade.core.data.source.remote.network

import com.example.submissionmade.BuildConfig
import com.example.submissionmade.core.data.source.remote.response.ListMovieResponse
import com.example.submissionmade.core.data.source.remote.response.ListTvShowResponse
import com.example.submissionmade.core.data.source.remote.response.MovieResponse
import com.example.submissionmade.core.data.source.remote.response.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("discover/movie")
    fun getMovie(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ): Call<ListMovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ): Call<List<MovieResponse>>

    @GET("discover/tv")
    fun getTvShow(@Query("api_key") apiKey: String = BuildConfig.API_KEY): Call<ListTvShowResponse>

    @GET("tv/{tv_id}")
    fun getTvShowDetails(
        @Path("tv_id") id: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ): Call<List<TvShowResponse>>
}