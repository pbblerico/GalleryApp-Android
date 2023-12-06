package com.example.galleryapp.presentation.folder

import android.util.Log
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.galleryapp.R
import com.example.galleryapp.account.presentation.adapter.ImageAdapter
import com.example.galleryapp.base.BaseFragment
import com.example.galleryapp.data.models.Image
import com.example.galleryapp.databinding.FragmentFolderBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FolderFragment : BaseFragment<FragmentFolderBinding>(R.layout.fragment_folder) {
    private val adapter: ImageAdapter = ImageAdapter()
    override fun getViewBinding(): FragmentFolderBinding = FragmentFolderBinding.inflate(layoutInflater)

    override fun setUpViews() {

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


        val pickMedia = registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(10)) { uri ->
            // Callback is invoked after the user selects a media item or closes the
            // photo picker.
            if (uri != null) {
                Log.d("PhotoPicker", "Selected URI: $uri")
            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }
        binding.floatingButton.setOnClickListener {
//            val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
//            sharedPref.edit().putBoolean(Preferences.AUTHORIZED.name, true).apply()
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    }

    override fun observeState() {
    }

}