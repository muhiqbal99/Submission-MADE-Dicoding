package com.example.submissionmade

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.submissionmade.core.ui.ViewPagerAdapter
import com.example.submissionmade.databinding.ActivityHomeBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = viewPagerAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = movieTabs[position]
        }.attach()

    }

    private val movieTabs = arrayOf(
        "Movie",
        "TvShows",
        "Favorite"
    )

}