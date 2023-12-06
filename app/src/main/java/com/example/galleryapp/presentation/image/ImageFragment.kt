package com.example.galleryapp.presentation.image

import com.example.galleryapp.R
import com.example.galleryapp.base.BaseFragment
import com.example.galleryapp.databinding.FragmentImageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageFragment : BaseFragment<FragmentImageBinding>(R.layout.fragment_image) {
    override fun getViewBinding(): FragmentImageBinding = FragmentImageBinding.inflate(layoutInflater)

    override fun setUpViews() {
    }

    override fun observeState() {
    }


}