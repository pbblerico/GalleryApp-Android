package com.example.galleryapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.galleryapp.account.presentation.adapter.ImageAdapter
import com.example.galleryapp.data.models.Image
import com.example.galleryapp.databinding.FragmentFolderBinding


class FolderFragment : Fragment() {
    private lateinit var binding: FragmentFolderBinding
    private val adapter: ImageAdapter = ImageAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFolderBinding.inflate(layoutInflater, container, false)

        binding.imageAdapter.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.imageAdapter.adapter = adapter

        val list = listOf(
            Image(1, "asdsfsdfs"),
            Image(1, "asdsfsdfs"),
            Image(1, "asdsfsdfs"),
            Image(1, "asdsfsdfs"),
            Image(1, "asdsfsdfs"),
            Image(1, "asdsfsdfs"),
            Image(1, "asdsfsdfs"),
            Image(1, "asdsfsdfs"),
            Image(1, "asdsfsdfs"),
            Image(1, "asdsfsdfs"),
        )
        adapter.submitList(list)
        adapter.click = {
            Navigation.findNavController(binding.root).navigate(R.id.imageFragment)
        }

        return binding.root
    }

}