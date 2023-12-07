package com.example.galleryapp.presentation.image

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import coil.size.Scale
import com.example.galleryapp.R
import com.example.galleryapp.base.BaseFragment
import com.example.galleryapp.databinding.FragmentImageBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ImageFragment : BaseFragment<FragmentImageBinding>(R.layout.fragment_image) {
    private val viewModel: ImageViewModel by viewModels()
    override fun getViewBinding(): FragmentImageBinding = FragmentImageBinding.inflate(layoutInflater)

    override fun setUpViews() {

        binding.toolbar.trailingIconVisibility = viewModel.isAuthorized()
        binding.toolbar.isClickable = viewModel.isAuthorized()


        val receivedId = arguments?.getInt("id")
        val receivedUrl = arguments?.getString("url")

        receivedId?.let {

            viewModel.handleEvent(ImageContract.ImageEvent.LoadImageFromApi(it))

            binding.toolbar.trailingIconAction = {
                viewModel.handleEvent(ImageContract.ImageEvent.SaveImage(it))
            }
        } ?: {
            receivedUrl?.let {
                viewModel.handleEvent(ImageContract.ImageEvent.LoadImageFromUrl(it))
            }
        }
    }

    override fun observeState() {

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect {state ->
                when(state) {
                    is ImageContract.ImageState.LoadSuccess -> {
                        binding.loading.isVisible = false
                        binding.image.load(state.photo){
                            crossfade(true)
                            placeholder(R.drawable.placeholder)
                            scale(Scale.FILL)
                        }
                    }
                    is ImageContract.ImageState.Loading -> {
                        binding.loading.isVisible = true
                    }
                    else -> {
                        binding.loading.isVisible = false

                    }
                }
            }
        }
    }


}