package com.example.galleryapp.account.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.example.galleryapp.R
import com.example.galleryapp.data.models.Photo
import com.example.galleryapp.databinding.ItemImageBinding

class PicturePagingAdapter :
    PagingDataAdapter<Photo, PicturePagingAdapter.PictureViewHolder>(PictureDiffUtils()) {

    class PictureViewHolder(private val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Photo) {
            item?.urlSource?.let { Log.d("item", it.original.toString()) }

            item.urlSource?.let {
//                val url = it.substring(0, it.length - 1) + ".jpeg"
//                Log.d("item", url)
                binding.image.load(it.original)
                {
                    crossfade(true)
                    placeholder(R.drawable.anonymous)
                    scale(Scale.FILL)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        return PictureViewHolder(
            ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
}

class PictureDiffUtils : DiffUtil.ItemCallback<Photo>() {

    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem == newItem
    }

}