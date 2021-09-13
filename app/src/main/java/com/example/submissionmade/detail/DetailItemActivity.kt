package com.example.submissionmade.detail

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.example.submissionmade.R
import com.example.submissionmade.core.domain.model.Item
import com.example.submissionmade.core.ui.ViewModelFactory
import com.example.submissionmade.core.utils.Constants.Companion.BASE_IMG
import com.example.submissionmade.databinding.ActivityDetailBinding
import jp.wasabeef.glide.transformations.BlurTransformation

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailItemItemViewModel: DetailItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        detailItemItemViewModel = ViewModelProvider(this, factory)[DetailItemViewModel::class.java]

        val detailItem = intent.getParcelableExtra<Item>(EXTRA_DATA)
        showDetailItem(detailItem)
    }

    private fun showDetailItem(detailItem: Item?) {
        detailItem?.let {
            with(binding) {
                tvOverview.text = detailItem.overview
                tvRating.text = detailItem.score.toString()
                tvReleaseDate.text = detailItem.releaseDate
                tvTitle.text = detailItem.title

                setPoster(BASE_IMG + detailItem.poster, ivPoster)
                setHeader(BASE_IMG + detailItem.poster, ivHeader)

                var statusFavorite = detailItem.isFavorite
                setFavoriteState(statusFavorite)
                btnFav.setOnClickListener {
                    statusFavorite = !statusFavorite
                    detailItemItemViewModel.setFavoriteItem(detailItem, statusFavorite)
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
            .apply(bitmapTransform(BlurTransformation(15, 3))
                .placeholder(R.drawable.logo)
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