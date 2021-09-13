package com.example.submissionmade.core.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.submissionmade.favorite.FavoriteFragments
import com.example.submissionmade.movie.MovieFragment
import com.example.submissionmade.tvshow.TvShowFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount() = NUM_TABS

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return MovieFragment()
            1 -> return TvShowFragment()
            2 -> return FavoriteFragments()
        }
        return Fragment()
    }

    companion object {
        const val NUM_TABS = 3
    }
}