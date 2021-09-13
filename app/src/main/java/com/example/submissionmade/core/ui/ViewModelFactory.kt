package com.example.submissionmade.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.submissionmade.core.di.Injection
import com.example.submissionmade.core.domain.usecase.ItemUseCase
import com.example.submissionmade.detail.DetailItemViewModel
import com.example.submissionmade.favorite.FavoriteViewModel
import com.example.submissionmade.movie.MovieViewModel
import com.example.submissionmade.tvshow.TvShowViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val itemUseCase: ItemUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                ViewModelFactory(Injection.provideItemUseCase(context)).apply {
                    instance = this
                }
            }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> MovieViewModel(
                itemUseCase) as T
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> TvShowViewModel(
                itemUseCase) as T
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> FavoriteViewModel(
                itemUseCase) as T
            modelClass.isAssignableFrom(DetailItemViewModel::class.java) -> DetailItemViewModel(
                itemUseCase) as T
            else -> throw IllegalArgumentException("Unknown ViewModel: " + modelClass.name)
        }
    }
}