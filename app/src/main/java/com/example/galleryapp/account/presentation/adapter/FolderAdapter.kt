package com.example.galleryapp.account.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.galleryapp.data.models.Folder
import com.example.galleryapp.databinding.ItemFolderBinding

class FolderAdapter: ListAdapter<Folder, FolderAdapter.FolderViewHolder>(FolderDiffUtils) {

    var click: ((String?) -> Unit)? = null
    object FolderDiffUtils: DiffUtil.ItemCallback<Folder>() {
        override fun areItemsTheSame(oldItem: Folder, newItem: Folder): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Folder, newItem: Folder): Boolean {
            return oldItem == newItem
        }
    }

    inner class FolderViewHolder(private val binding: ItemFolderBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Folder) {
            binding.folderTitle.text = item.name

            itemView.setOnClickListener {
                click?.invoke(item.name)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderViewHolder {
        return FolderViewHolder(
            ItemFolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: FolderViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}