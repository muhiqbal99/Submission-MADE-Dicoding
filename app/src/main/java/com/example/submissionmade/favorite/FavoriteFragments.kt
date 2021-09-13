package com.example.submissionmade.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.submissionmade.core.domain.model.Item
import com.example.submissionmade.core.ui.ItemAdapter
import com.example.submissionmade.core.ui.ViewModelFactory
import com.example.submissionmade.databinding.FragmentFavoriteBinding
import com.example.submissionmade.detail.DetailActivity

class FavoriteFragments : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding
    private val favoriteAdapter = ItemAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): ScrollView? {
        _binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.rvItems?.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 2)
            adapter = favoriteAdapter
        }

        favoriteAdapter.setOnClickCallback(object : ItemAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Item) {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, data)
                startActivity(intent)
            }
        })

        observeItem()
    }

    private fun observeItem() {
        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

        showLoading("loading")
        viewModel.getFavoriteItem().observe(viewLifecycleOwner, { items ->
            if (items.isNullOrEmpty()) {
                showLoading("error")
            } else {
                showLoading("success")
                favoriteAdapter.setData(items)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        favoriteAdapter.notifyDataSetChanged()
    }

    private fun showLoading(state: String) {
        when (state) {
            "loading" -> binding?.progressBar?.visibility = View.VISIBLE
            "success" -> {
                binding?.progressBar?.visibility = View.GONE
                binding?.rvItems?.visibility = View.VISIBLE
                binding?.layoutError?.activityError?.visibility = View.GONE
            }
            else -> {
                binding?.progressBar?.visibility = View.GONE
            }
        }
    }
}