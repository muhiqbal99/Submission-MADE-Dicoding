package com.example.submissionmade.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.submissionmade.core.data.Resource
import com.example.submissionmade.core.domain.model.Items
import com.example.submissionmade.core.ui.ItemAdapter
import com.example.submissionmade.core.ui.ViewModelFactory
import com.example.submissionmade.databinding.FragmentItemBinding
import com.example.submissionmade.detail.DetailActivity

class TvShowFragment : Fragment() {

    private var _binding: FragmentItemBinding? = null
    private val binding get() = _binding
    private val tvShowAdapter = ItemAdapter()

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
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]


            viewModel.getTvShow().observe(viewLifecycleOwner, { tvShow ->
                if (tvShow != null) {
                    when (tvShow) {
                        is Resource.Loading -> showLoading(true)
                        is Resource.Success -> {
                            showLoading(false)
                            tvShowAdapter.setData(tvShow.data)
                            tvShowAdapter.notifyDataSetChanged()
                        }
                        is Resource.Error -> {
                            showLoading(false)
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            binding?.rvItems?.apply {
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(context, 2)
                adapter = tvShowAdapter
            }

            tvShowAdapter.setOnClickCallback(object : ItemAdapter.OnItemClickCallback {
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

    companion object {
        const val CLICK_TV = 2
    }
}