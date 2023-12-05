package com.example.galleryapp.presentation.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.galleryapp.R
import com.example.galleryapp.account.presentation.adapter.FolderAdapter
import com.example.galleryapp.data.models.Folder
import com.example.galleryapp.databinding.FragmentAccountBinding


class AccountFragment : Fragment() {
    private lateinit var binding: FragmentAccountBinding
    private val adapter: FolderAdapter = FolderAdapter()


    private var isShow = true
    private var scrollRange = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountBinding.inflate(inflater, container, false)
//        binding.toolBar.inflateMenu(R.menu.account_app_bar_logged_user)
//        binding.toolbar.trailingIcon

//        binding.toolbar.trailingIconAction = {
////            Toast.makeText(requireContext(), "${binding.toolbar.scrollY}", Toast.LENGTH_SHORT).show()
//            Navigation.findNavController(binding.root).navigate(R.id.loginFragment)
//        }

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

//        binding.toolbar.setSafeOnClickListener {
//            if
//        }

        return binding.root
    }

    private fun blockToolbar(motion: MotionEvent): Boolean {
        return false
    }
}