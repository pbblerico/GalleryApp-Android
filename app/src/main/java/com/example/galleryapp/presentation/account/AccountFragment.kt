package com.example.galleryapp.presentation.account

import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.galleryapp.R
import com.example.galleryapp.account.presentation.adapter.FolderAdapter
import com.example.galleryapp.base.BaseFragment
import com.example.galleryapp.data.models.Folder
import com.example.galleryapp.databinding.FragmentAccountBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AccountFragment : BaseFragment<FragmentAccountBinding>(R.layout.fragment_account) {
    override fun getViewBinding(): FragmentAccountBinding = FragmentAccountBinding.inflate(layoutInflater)

    private val adapter: FolderAdapter = FolderAdapter()

    override fun setUpViews() {
        binding.folderAdapter.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.folderAdapter.adapter = adapter

        val list = listOf(
            Folder(0, "one really long long title that I want to test the", ""),
            Folder(1, "two", ""),
            Folder(2, "four", ""),
            Folder(3, "three", ""),
        )
        adapter.submitList(list)
        adapter.click = {
            Navigation.findNavController(binding.root).navigate(R.id.folderFragment)
        }
    }

    override fun observeState() {
        TODO("Not yet implemented")
    }
}