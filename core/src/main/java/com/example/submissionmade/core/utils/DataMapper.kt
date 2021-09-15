package com.example.submissionmade.core.utils

import com.example.submissionmade.core.data.source.local.entity.ItemsEntity
import com.example.submissionmade.core.data.source.remote.response.MovieResponse
import com.example.submissionmade.core.data.source.remote.response.TvShowResponse
import com.example.submissionmade.core.domain.model.Items

object DataMapper {
    fun movieMapResponsesToEntities(input: List<MovieResponse>): List<ItemsEntity> {
        val movieList =
            ArrayList<ItemsEntity>()
        input.map {
            val movie = ItemsEntity(
                id = it.id,
                title = it.title,
                releaseDate = it.releaseDate,
                score = it.score,
                overview = it.overview,
                poster = it.poster,
                header = it.header,
                type = "movie"
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun tvShowMapResponsesToEntities(input: List<TvShowResponse>): List<ItemsEntity> {
        val tvShowList =
            ArrayList<ItemsEntity>()
        input.map {
            val tvShow = ItemsEntity(
                id = it.id,
                title = it.title,
                releaseDate = it.releaseDate,
                score = it.score,
                overview = it.overview,
                poster = it.poster,
                header = it.header,
                type = "tvshow"
            )
            tvShowList.add(tvShow)
        }
        return tvShowList
    }

    fun mapEntitiesToDomain(input: List<ItemsEntity>): List<Items> =
        input.map {
            Items(
                id = it.id,
                title = it.title,
                releaseDate = it.releaseDate,
                score = it.score,
                overview = it.overview,
                poster = it.poster,
                header = it.header,
                type = it.type,
                isFavorite = it.isFavorite
            )
        }

    fun movieMapEntitiesToDomain(input: List<ItemsEntity>): List<Items> =
        input.map {
            Items(
                id = it.id,
                title = it.title,
                releaseDate = it.releaseDate,
                score = it.score,
                overview = it.overview,
                poster = it.poster,
                header = it.header,
                type = "movie",
                isFavorite = it.isFavorite
            )
        }

    fun tvShowMapEntitiesToDomain(input: List<ItemsEntity>): List<Items> =
        input.map {
            Items(
                id = it.id,
                title = it.title,
                releaseDate = it.releaseDate,
                score = it.score,
                overview = it.overview,
                poster = it.poster,
                header = it.header,
                type = "tvshow",
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntities(input: Items) =
        ItemsEntity(
            id = input.id,
            title = input.title,
            releaseDate = input.releaseDate,
            score = input.score,
            overview = input.overview,
            poster = input.poster,
            header = input.header,
            type = input.type,
            isFavorite = input.isFavorite
        )

}