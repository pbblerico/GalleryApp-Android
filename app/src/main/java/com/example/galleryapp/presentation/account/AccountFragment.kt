package com.example.galleryapp.presentation.account

import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.galleryapp.R
import com.example.galleryapp.account.presentation.adapter.FolderAdapter
import com.example.galleryapp.base.BaseFragment
import com.example.galleryapp.data.models.Folder
import com.example.galleryapp.databinding.FragmentAccountBinding
import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AccountFragment : BaseFragment<FragmentAccountBinding>(R.layout.fragment_account) {

    private val viewModel: AccountViewModel by viewModels()
    override fun getViewBinding(): FragmentAccountBinding = FragmentAccountBinding.inflate(layoutInflater)

    private val adapter: FolderAdapter = FolderAdapter()

    override fun setUpViews() {
        binding.folderAdapter.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.folderAdapter.adapter = adapter

        val list = listOf(
            Folder("public"),
            Folder( "private"),
        )
        val storage = Firebase.storage.reference
        val directory = storage.child("users/${viewModel.getCurrentUser()?.uid}/images")

        val folders: MutableList<Folder> = mutableListOf()



        adapter.submitList(list)
        adapter.click = {
            Navigation.findNavController(binding.root).navigate(R.id.folderFragment)
        }
    }

    override fun observeState() {
    }
}