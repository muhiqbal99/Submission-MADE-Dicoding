package com.example.submissionmade.core.utils

import com.example.submissionmade.core.data.source.local.entity.ItemsEntity
import com.example.submissionmade.core.data.source.remote.response.MovieResponse
import com.example.submissionmade.core.data.source.remote.response.TvShowResponse
import com.example.submissionmade.core.domain.model.Item

object DataMapper {
    fun movieMapResponsesToEntities(input: List<MovieResponse>): List<ItemsEntity> {
        val movieList = ArrayList<ItemsEntity>()
        input.map {
            val movie = ItemsEntity(
                id = it.id,
                title = it.title,
                releaseDate = it.releaseDate,
                score = it.score,
                overview = it.overview,
                poster = it.poster,
                type = "movie"
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun tvMapResponsesToEntities(input: List<TvShowResponse>): List<ItemsEntity> {
        val tvShowList = ArrayList<ItemsEntity>()
        input.map {
            val tvshow = ItemsEntity(
                id = it.id,
                title = it.title,
                releaseDate = it.releaseDate,
                score = it.score,
                overview = it.overview,
                poster = it.poster,
                type = "tvshow"
            )
            tvShowList.add(tvshow)
        }
        return tvShowList
    }

    fun movieIdMapResponsesToEntities(input: List<MovieResponse>): List<ItemsEntity> =
        input.map {
            ItemsEntity(
                id = it.id,
                title = it.title,
                releaseDate = it.releaseDate,
                score = it.score,
                overview = it.overview,
                poster = it.poster,
                type = "movie"
            )
        }

    fun tvIdMapResponsesToEntities(input: List<TvShowResponse>): List<ItemsEntity> =
        input.map {
            ItemsEntity(
                id = it.id,
                title = it.title,
                releaseDate = it.releaseDate,
                score = it.score,
                overview = it.overview,
                poster = it.poster,
                type = "tvshow"
            )
        }

    fun mapEntitiesToDomain(input: List<ItemsEntity>): List<Item> =
        input.map {
            Item(
                id = it.id,
                title = it.title,
                releaseDate = it.releaseDate,
                score = it.score,
                overview = it.overview,
                poster = it.poster,
                type = it.type,
                isFavorite = it.isFavorite
            )
        }

    fun movieMapEntitiesToDomain(input: List<ItemsEntity>): List<Item> =
        input.map {
            Item(
                id = it.id,
                title = it.title,
                releaseDate = it.releaseDate,
                score = it.score,
                overview = it.overview,
                poster = it.poster,
                type = "movie",
                isFavorite = it.isFavorite
            )
        }

    fun tvShowMapEntitiesToDomain(input: List<ItemsEntity>): List<Item> =
        input.map {
            Item(
                id = it.id,
                title = it.title,
                releaseDate = it.releaseDate,
                score = it.score,
                overview = it.overview,
                poster = it.poster,
                type = "tvshow",
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntities(input: Item) = ItemsEntity(
        id = input.id,
        title = input.title,
        releaseDate = input.releaseDate,
        score = input.score,
        overview = input.overview,
        poster = input.poster,
        type = input.type,
        isFavorite = input.isFavorite
    )

}