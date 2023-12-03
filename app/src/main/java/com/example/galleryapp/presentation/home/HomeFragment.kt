package com.example.galleryapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.galleryapp.account.presentation.adapter.PicturePagingAdapter
import com.example.galleryapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment: Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private val pagingAdapter = PicturePagingAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.rv.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rv.adapter = pagingAdapter

        viewModel.data.observe(viewLifecycleOwner) {
            pagingAdapter.submitData(lifecycle, it)
        }


//        lifecycleScope.launch {
//            Log.d("ge;", "sdjfsaldf;sadflas")
//        }
//        lifecycleScope.launch {
//            try {
//                val result = api.getCuratedPhotos().body()
//                result?.photos?.forEach {
//                    Log.d("request", it.url.toString())
//                }
//            } catch (e: Exception) {
//                Log.d("error", e.message ?: "errrorrrrr")
//            }
//        }

        return binding.root
    }
}