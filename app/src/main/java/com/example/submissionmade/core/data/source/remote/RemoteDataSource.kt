package com.example.submissionmade.core.data.source.remote

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.submissionmade.core.data.source.remote.network.RetrofitInstance
import com.example.submissionmade.core.data.source.remote.response.ListMovieResponse
import com.example.submissionmade.core.data.source.remote.response.ListTvShowResponse
import com.example.submissionmade.core.data.source.remote.response.MovieResponse
import com.example.submissionmade.core.data.source.remote.response.TvShowResponse
import com.example.submissionmade.core.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    private var handler = Handler(Looper.getMainLooper())

    fun getMovie(): LiveData<ApiResponse<List<MovieResponse>>> {
        val resultData = MutableLiveData<ApiResponse<List<MovieResponse>>>()
        EspressoIdlingResource.increment()
        handler.postDelayed({
            RetrofitInstance.apiService.getMovie().enqueue(object : Callback<ListMovieResponse> {
                override fun onFailure(call: Call<ListMovieResponse>, t: Throwable) {
                    resultData.value = ApiResponse.Error(t.message.toString())
                }

                override fun onResponse(
                    call: Call<ListMovieResponse>,
                    responseList: Response<ListMovieResponse>,
                ) {
                    val dataArray = responseList.body()?.results
                    resultData.value =
                        if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
                }

            })
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultData
    }

    fun getMovieDetail(id: Int): LiveData<ApiResponse<List<MovieResponse>>> {
        val resultData = MutableLiveData<ApiResponse<List<MovieResponse>>>()
        EspressoIdlingResource.increment()
        handler.postDelayed({
            RetrofitInstance.apiService.getMovieDetails(id)
                .enqueue(object : Callback<List<MovieResponse>> {
                    override fun onFailure(call: Call<List<MovieResponse>>, t: Throwable) {
                        resultData.value = ApiResponse.Error(t.message.toString())
                    }

                    override fun onResponse(
                        call: Call<List<MovieResponse>>,
                        response: Response<List<MovieResponse>>,
                    ) {
                        val dataArray = response.body()
                        resultData.value =
                            if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
                    }

                })
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultData
    }

    fun getTvShow(): LiveData<ApiResponse<List<TvShowResponse>>> {
        val resultData = MutableLiveData<ApiResponse<List<TvShowResponse>>>()
        EspressoIdlingResource.increment()
        handler.postDelayed({
            RetrofitInstance.apiService.getTvShow().enqueue(object : Callback<ListTvShowResponse> {
                override fun onFailure(call: Call<ListTvShowResponse>, t: Throwable) {
                    resultData.value = ApiResponse.Error(t.message.toString())
                }

                override fun onResponse(
                    call: Call<ListTvShowResponse>,
                    responseList: Response<ListTvShowResponse>,
                ) {
                    val dataArray = responseList.body()?.results
                    resultData.value =
                        if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
                }

            })
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultData
    }

    fun getTvShowDetail(id: Int): LiveData<ApiResponse<List<TvShowResponse>>> {
        val resultData = MutableLiveData<ApiResponse<List<TvShowResponse>>>()
        EspressoIdlingResource.increment()
        handler.postDelayed({
            RetrofitInstance.apiService.getTvShowDetails(id)
                .enqueue(object : Callback<List<TvShowResponse>> {
                    override fun onFailure(call: Call<List<TvShowResponse>>, t: Throwable) {
                        resultData.value = ApiResponse.Error(t.message.toString())
                    }

                    override fun onResponse(
                        call: Call<List<TvShowResponse>>,
                        response: Response<List<TvShowResponse>>,
                    ) {
                        val dataArray = response.body()
                        resultData.value =
                            if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
                    }

                })
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultData
    }

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }
}