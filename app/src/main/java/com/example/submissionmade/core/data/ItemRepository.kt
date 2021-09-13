package com.example.submissionmade.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.submissionmade.core.data.source.local.LocalDataSource
import com.example.submissionmade.core.data.source.remote.ApiResponse
import com.example.submissionmade.core.data.source.remote.RemoteDataSource
import com.example.submissionmade.core.data.source.remote.response.MovieResponse
import com.example.submissionmade.core.data.source.remote.response.TvShowResponse
import com.example.submissionmade.core.domain.model.Item
import com.example.submissionmade.core.domain.repository.IItemRepository
import com.example.submissionmade.core.utils.AppExecutors
import com.example.submissionmade.core.utils.DataMapper

class ItemRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
) : IItemRepository {

    companion object {
        @Volatile
        private var instance: ItemRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors,
        ): ItemRepository =
            instance ?: synchronized(this) {
                instance ?: ItemRepository(remoteData, localData, appExecutors)
            }
    }

    override fun getMovieItem(): LiveData<Resource<List<Item>>> {
        return object : NetworkBoundResource<List<Item>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<Item>> {
                return Transformations.map(localDataSource.getMovieItem()) {
                    DataMapper.movieMapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Item>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getMovie()

            override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.movieMapResponsesToEntities(data)
                localDataSource.insertItems(movieList)
            }

        }.asLiveData()
    }

    override fun getTvShowItem(): LiveData<Resource<List<Item>>> {
        return object :
            NetworkBoundResource<List<Item>, List<TvShowResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<Item>> {
                return Transformations.map(localDataSource.getTvShowItem()) {
                    DataMapper.tvShowMapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Item>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getTvShow()

            override fun saveCallResult(data: List<TvShowResponse>) {
                val tvShowList = DataMapper.tvMapResponsesToEntities(data)
                localDataSource.insertItems(tvShowList)
            }
        }.asLiveData()
    }

//    override fun getDetailMovie(id: Int): LiveData<Resource<Item>> {
//        return object : NetworkBoundResource<Item, List<MovieResponse>>(appExecutors) {
//            override fun loadFromDB(): LiveData<Item> {
//                return Transformations.map(localDataSource.getMovieById(id)) {
//                    DataMapper.movieIdMapResponsesToEntities(it)
//                }
//            }
//
//            override fun shouldFetch(data: Item?): Boolean =
//                data == null
//
//            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
//                remoteDataSource.getMovieDetail(id)
//
//            override fun saveCallResult(data: List<MovieResponse>) {
//                val movieDetail = DataMapper.movieIdMapResponsesToEntities(data)
//                localDataSource.insertDetail(movieDetail)
//            }
//
//        }.asLiveData()
//    }
//
//    override fun getDetailTvShow(id: Int): LiveData<Resource<Item>> {
//        return object : NetworkBoundResource<Item, List<TvShowResponse>>(appExecutors) {
//            override fun loadFromDB(): LiveData<Item> =
//                localDataSource.getTvShowById(id)
//
//            override fun shouldFetch(data: Item?): Boolean =
//                data == null
//
//            override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> =
//                remoteDataSource.getTvShowDetail(id)
//
//            override fun saveCallResult(data: List<TvShowResponse>) {
//                val tvShowDetail = DataMapper.tvIdMapResponsesToEntities(data)
//                localDataSource.insertDetail(tvShowDetail)
//            }
//        }.asLiveData()
//    }

    override fun getFavoriteItem(): LiveData<List<Item>> {
        return Transformations.map(localDataSource.getFavoriteItemList()) {
            DataMapper.mapEntitiesToDomain(it)
        }
    }


    override fun setFavorite(items: Item, state: Boolean) {
        val itemEntity = DataMapper.mapDomainToEntities(items)
        appExecutors.diskIO().execute { localDataSource.setFavorite(itemEntity, state) }
    }
}