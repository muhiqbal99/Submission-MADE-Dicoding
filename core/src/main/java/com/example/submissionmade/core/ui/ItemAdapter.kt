package com.example.submissionmade.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submissionmade.core.R
import com.example.submissionmade.core.databinding.ItemListBinding
import com.example.submissionmade.core.domain.model.Items
import com.example.submissionmade.core.utils.Constants.Companion.BASE_IMG

class ItemAdapter :
    RecyclerView.Adapter<ItemAdapter.MovieMyViewHolder>() {

    private var mData: List<Items> = emptyList()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Items)
    }

    fun setData(items: List<Items>?) {
        if (items == null) return
        this.mData = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MovieMyViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return MovieMyViewHolder(mView)
    }

    override fun onBindViewHolder(holder: MovieMyViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    override fun getItemCount(): Int = mData.size

    inner class MovieMyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var binding = ItemListBinding.bind(itemView)
        fun bind(itemsEntityItems: Items) {
            with(binding) {
                Glide.with(itemView)
                    .load(BASE_IMG + itemsEntityItems.poster)
                    .into(imgLogo)

                tvTitle.text = itemsEntityItems.title
                tvRating.text = itemsEntityItems.score.toString()
                root.setOnClickListener {
                    onItemClickCallback?.onItemClicked(itemsEntityItems)
                }
            }
        }
    }

}
