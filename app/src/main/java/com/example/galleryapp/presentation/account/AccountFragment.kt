package com.example.galleryapp.presentation.account

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.galleryapp.R
import com.example.galleryapp.account.presentation.adapter.ImageAdapter
import com.example.galleryapp.base.BaseFragment
import com.example.galleryapp.databinding.FragmentAccountBinding
import com.example.galleryapp.utils.enums.Destination
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AccountFragment : BaseFragment<FragmentAccountBinding>(R.layout.fragment_account) {

    private val viewModel: AccountViewModel by viewModels()
    override fun getViewBinding(): FragmentAccountBinding =
        FragmentAccountBinding.inflate(layoutInflater)

    private val adapter: ImageAdapter = ImageAdapter()

    override fun setUpViews() {
        binding.folderAdapter.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.folderAdapter.adapter = adapter

//        val list = listOf(
//            Folder(getString(R.string.created)),
//            Folder(getString(R.string.saved))
//        )
//        val storage = Firebase.storage.reference
//        val directory = storage.child("users/${viewModel.getCurrentUser()?.uid}/images")
//
//        val folders: MutableList<Folder> = mutableListOf()
        val uid = viewModel.getCurrentUser()?.uid
        Log.d("uid", uid.toString())
        if (uid != null) {
            viewModel.getPublicImages(uid)
            viewModel.getUserInfo()
        }

        viewModel.images.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.user.observe(viewLifecycleOwner) {user ->
            user?.nickname?.let {
                binding.avatar.name = it
            }
        }

        adapter.click = {
            val bundle = Bundle()
            bundle.putString("url", it)
            Navigation.findNavController(binding.root).navigate(Destination.IMAGE.fragmentId, bundle)
        }

    }

    override fun observeState() {
    }
}