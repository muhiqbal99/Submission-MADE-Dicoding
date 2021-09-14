package com.example.submissionmade.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.submissionmade.R
import com.example.submissionmade.core.data.Resource
import com.example.submissionmade.core.domain.model.Items
import com.example.submissionmade.core.ui.ItemAdapter
import com.example.submissionmade.databinding.FragmentItemBinding
import com.example.submissionmade.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private var _binding: FragmentItemBinding? = null
    private val binding get() = _binding
    private val movieViewModel: MovieViewModel by viewModels()
    private val itemAdapter = ItemAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): ConstraintLayout? {
        _binding = FragmentItemBinding.inflate(layoutInflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            movieViewModel.getMovies().observe(viewLifecycleOwner, { movies ->
                if (movies != null) {
                    when (movies) {
                        is Resource.Loading -> showLoading(true)
                        is Resource.Success -> {
                            showLoading(false)
                            itemAdapter.setData(movies.data)
                        }
                        is Resource.Error -> {
                            showLoading(false)
                            Toast.makeText(context, R.string.error, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            binding?.rvItems?.apply {
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(context, 2)
                adapter = itemAdapter
            }

            itemAdapter.setOnClickCallback(object : ItemAdapter.OnItemClickCallback {
                override fun onItemClicked(data: Items) {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_DATA, data)
                    startActivity(intent)
                }
            })
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding?.progressBar?.visibility = View.VISIBLE
        } else {
            binding?.progressBar?.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}