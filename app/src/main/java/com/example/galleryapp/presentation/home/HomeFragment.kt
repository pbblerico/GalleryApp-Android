package com.example.galleryapp.presentation.home

import android.os.Bundle
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
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by viewModels()
    private val pagingAdapter = PicturePagingAdapter()
    override fun getViewBinding(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    override fun setUpViews() {

        binding.rv.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rv.adapter = pagingAdapter

        pagingAdapter.click = {
            val bundle = Bundle()
            bundle.putInt("id", it?.id ?: 0)
            Navigation.findNavController(binding.root).navigate(Destination.IMAGE.fragmentId, bundle)
        }

        viewModel.data.observe(viewLifecycleOwner) {
            pagingAdapter.submitData(lifecycle, it)
        }


    }

    override fun observeState() {
    }
}