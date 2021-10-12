package com.example.nytimes.presentation.most_viewed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nytimes.databinding.ItemMostViewedBinding
import com.example.nytimes.domain.model.MostViewed

class MostViewAdapter(private val items: List<MostViewed>) : RecyclerView.Adapter<MostViewAdapter.MostViewedVH>() {


    inner class MostViewedVH(private val binding: ItemMostViewedBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(item: MostViewed) {
            binding.tvTitle.text = item.title
            binding.tvPublishData.text = item.publishedDate
            binding.tvByline.text = item.byline
            Glide.with(binding.root).load(item.image).centerCrop().into(binding.ivImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MostViewedVH {
        return MostViewedVH(ItemMostViewedBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MostViewedVH, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}