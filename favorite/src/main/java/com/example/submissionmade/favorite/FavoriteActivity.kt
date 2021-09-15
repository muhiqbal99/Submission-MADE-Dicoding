package com.example.submissionmade.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.submissionmade.core.domain.model.Items
import com.example.submissionmade.core.ui.ItemAdapter
import com.example.submissionmade.detail.DetailActivity
import com.example.submissionmade.di.FavoriteModuleDepedencies
import com.example.submissionmade.favorite.databinding.FragmentFavoriteBinding
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val favoriteViewModel: FavoriteViewModel by viewModels {
        factory
    }

    private lateinit var binding: FragmentFavoriteBinding
    private val favoriteAdapter = ItemAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavoriteComponen.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    FavoriteModuleDepedencies::class.java
                )
            )
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)

        binding = FragmentFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeItem()
    }

    private fun observeItem() {

        showLoading("loading")

        binding.rvItems.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 2)
            adapter = favoriteAdapter
        }

        favoriteAdapter.setOnClickCallback(object : ItemAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Items) {
                val intent = Intent(this@FavoriteActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, data)
                startActivity(intent)
            }
        })

        favoriteViewModel.getFavoriteItem.observe(this, { items ->
            if (items.isNullOrEmpty()) {
                showLoading("error")
            } else {
                showLoading("success")
                favoriteAdapter.setData(items)
            }
        })
    }

    private fun showLoading(state: String) {
        when (state) {
            "loading" -> binding.progressBar.visibility = View.VISIBLE
            "success" -> {
                binding.progressBar.visibility = View.GONE
                binding.rvItems.visibility = View.VISIBLE
                binding.layoutError.activityError.visibility = View.GONE
            }
            else -> {
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    override fun onStop() {
        binding.rvItems.adapter = null
        super.onStop()
    }
}