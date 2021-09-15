package com.example.submissionmade.detail

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.submissionmade.R
import com.example.submissionmade.core.domain.model.Items
import com.example.submissionmade.core.utils.Constants.Companion.BASE_IMG
import com.example.submissionmade.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailItemItemViewModel: DetailItemViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailItem = intent.getParcelableExtra<Items>(EXTRA_DATA)
        showDetailItem(detailItem)
    }

    private fun showDetailItem(detailItems: Items?) {
        detailItems?.let {
            with(binding) {
                tvOverview.text = detailItems.overview
                tvRating.text = detailItems.score.toString()
                tvReleaseDate.text = detailItems.releaseDate
                tvTitle.text = detailItems.title

                setPoster(BASE_IMG + detailItems.poster, ivPoster)
                setHeader(BASE_IMG + detailItems.header, ivHeader)

                var statusFavorite = detailItems.isFavorite
                setFavoriteState(statusFavorite)
                btnFav.setOnClickListener {
                    statusFavorite = !statusFavorite
                    detailItemItemViewModel.setFavoriteItem(detailItems, statusFavorite)
                    setFavoriteState(statusFavorite)
                }
            }
        }
    }

    private fun setFavoriteState(state: Boolean) {
        when (state) {
            true -> binding.btnFav.setImageDrawable(ContextCompat.getDrawable(this,
                R.drawable.ic_bookmarks))
            false -> binding.btnFav.setImageDrawable(ContextCompat.getDrawable(this,
                R.drawable.ic_unbookmarks))
        }
    }

    private fun setPoster(url: String, view: ImageView) {
        Glide.with(this)
            .load(url)
            .transform(RoundedCorners(10))
            .apply(RequestOptions.placeholderOf(R.drawable.logo)
                .error(R.drawable.ic_error))
            .into(view)
    }

    private fun setHeader(url: String, view: ImageView) {
        Glide.with(this)
            .load(url)
            .transform(RoundedCorners(10))
            .apply(RequestOptions.placeholderOf(R.drawable.logo)
                .error(R.drawable.ic_error))
            .into(view)
    }

    override fun onBackPressed() {
        NavUtils.navigateUpFromSameTask(this)
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}