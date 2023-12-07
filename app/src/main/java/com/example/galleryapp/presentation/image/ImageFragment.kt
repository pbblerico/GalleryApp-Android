package com.example.galleryapp.presentation.image

import coil.load
import coil.size.Scale
import com.example.galleryapp.R
import com.example.galleryapp.base.BaseFragment
import com.example.galleryapp.databinding.FragmentImageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageFragment : BaseFragment<FragmentImageBinding>(R.layout.fragment_image) {
    override fun getViewBinding(): FragmentImageBinding = FragmentImageBinding.inflate(layoutInflater)

    override fun setUpViews() {

        val received = arguments?.getString("url")

        received?.let {
            binding.image.load(it){
                crossfade(true)
                placeholder(R.drawable.anonymous)
                scale(Scale.FILL)
            }
        }
    }

    override fun observeState() {
    }


}