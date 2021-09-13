package com.example.submissionmade.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submissionmade.R
import com.example.submissionmade.core.domain.model.Item
import com.example.submissionmade.core.utils.Constants.Companion.BASE_IMG
import com.example.submissionmade.databinding.ItemListBinding

class ItemAdapter :
    RecyclerView.Adapter<ItemAdapter.MovieMyViewHolder>() {

    private var mData: List<Item> = emptyList()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Item)
    }

    fun setData(items: List<Item>?) {
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
        fun bind(itemsEntityItem: Item) {
            with(binding) {
                Glide.with(itemView)
                    .load(BASE_IMG + itemsEntityItem.poster)
                    .into(imgLogo)

                tvTitle.text = itemsEntityItem.title
                tvRating.text = itemsEntityItem.score.toString()
                root.setOnClickListener {
                    onItemClickCallback?.onItemClicked(itemsEntityItem)
                }
            }
        }
    }

}
