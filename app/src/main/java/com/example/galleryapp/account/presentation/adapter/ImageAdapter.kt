package com.example.galleryapp.account.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.example.galleryapp.R
import com.example.galleryapp.data.models.Image
import com.example.galleryapp.databinding.ItemImageBinding

class ImageAdapter: ListAdapter<Image, ImageAdapter.ImageViewHolder>(ImageDiffUtils) {
    //todo base adapter realization

    var click: ((String) -> Unit)? = null
    object ImageDiffUtils: DiffUtil.ItemCallback<Image>() {
        override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem == newItem
        }
    }

    inner class ImageViewHolder(private val binding: ItemImageBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Image) {
            itemView.setOnClickListener {
                click?.invoke(item.image)
            }

            binding.image.load(item.image) {
                crossfade(true)
                placeholder(R.drawable.placeholder)
                scale(Scale.FILL)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}