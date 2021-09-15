package com.example.submissionmade.core.data

import com.example.submissionmade.core.data.source.local.LocalDataSource
import com.example.submissionmade.core.data.source.remote.RemoteDataSource
import com.example.submissionmade.core.data.source.remote.network.ApiResponse
import com.example.submissionmade.core.data.source.remote.response.MovieResponse
import com.example.submissionmade.core.data.source.remote.response.TvShowResponse
import com.example.submissionmade.core.domain.model.Items
import com.example.submissionmade.core.domain.repository.IItemRepository
import com.example.submissionmade.core.utils.AppExecutors
import com.example.submissionmade.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemsRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
) : IItemRepository {

    override fun getMovieItem(): Flow<Resource<List<Items>>> {
        return object :
            NetworkBoundResource<List<Items>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Items>> {
                return localDataSource.getMovieItem().map {
                    DataMapper.movieMapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Items>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getMovie()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.movieMapResponsesToEntities(data)
                localDataSource.insertItems(movieList)
            }

        }.asFlow()
    }

    override fun getTvShowItem(): Flow<Resource<List<Items>>> {
        return object :
            NetworkBoundResource<List<Items>, List<TvShowResponse>>() {
            override fun loadFromDB(): Flow<List<Items>> {
                return localDataSource.getTvShowItem().map {
                    DataMapper.tvShowMapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Items>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getTvShow()

            override suspend fun saveCallResult(data: List<TvShowResponse>) {
                val tvShowList = DataMapper.tvShowMapResponsesToEntities(data)
                localDataSource.insertItems(tvShowList)
            }

        }.asFlow()
    }

    override fun getFavoriteItem(): Flow<List<Items>> {
        return localDataSource.getFavoriteItemList().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavorite(items: Items, state: Boolean) {
        val itemEntity = DataMapper.mapDomainToEntities(items)
        appExecutors.diskIO().execute { localDataSource.setFavorite(itemEntity, state) }
    }
}