package com.example.galleryapp.presentation.home

import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.galleryapp.R
import com.example.galleryapp.account.presentation.adapter.PicturePagingAdapter
import com.example.galleryapp.base.BaseFragment
import com.example.galleryapp.databinding.FragmentHomeBinding
import com.example.galleryapp.utils.enums.Destination
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment: BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by viewModels()
    private val pagingAdapter = PicturePagingAdapter()
    override fun getViewBinding(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    override fun setUpViews() {

        binding.rv.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rv.adapter = pagingAdapter

        pagingAdapter.click = {
            Navigation.findNavController(binding.root).navigate(Destination.IMAGE.fragmentId)

        }

        viewModel.data.observe(viewLifecycleOwner) {
            pagingAdapter.submitData(lifecycle, it)
        }
    }

    override fun observeState() {
    }
}