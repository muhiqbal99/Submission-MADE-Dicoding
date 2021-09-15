package com.example.submissionmade.favorite

import android.content.Context
import com.example.submissionmade.di.FavoriteModuleDepedencies
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavoriteModuleDepedencies::class])
interface FavoriteComponen {

    fun inject(activity: FavoriteActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteModuleDepedencies: FavoriteModuleDepedencies): Builder
        fun build(): FavoriteComponen
    }

}