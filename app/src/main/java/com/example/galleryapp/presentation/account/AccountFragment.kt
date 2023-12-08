package com.example.galleryapp.presentation.account

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.galleryapp.R
import com.example.galleryapp.account.presentation.adapter.ImageAdapter
import com.example.galleryapp.base.BaseFragment
import com.example.galleryapp.databinding.FragmentAccountBinding
import com.example.galleryapp.utils.enums.Destination
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.storage
import dagger.hilt.android.AndroidEntryPoint
import java.util.UUID


@AndroidEntryPoint
class AccountFragment : BaseFragment<FragmentAccountBinding>(R.layout.fragment_account) {

    private val viewModel: AccountViewModel by viewModels()
    override fun getViewBinding(): FragmentAccountBinding =
        FragmentAccountBinding.inflate(layoutInflater)

    private val adapter: ImageAdapter = ImageAdapter()

    override fun setUpViews() {
        binding.folderAdapter.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.folderAdapter.adapter = adapter

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

        uploadImage()

    }

    private fun uploadImage() {
        val pickMedia =
            registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(10)) { uri ->
                if (uri != null) {
                    val storageRef = Firebase.storage.reference

                    for(u in uri) {
                        val file =
                            storageRef.child("users/${FirebaseAuth.getInstance().currentUser?.uid}/images/public/{${UUID.randomUUID()}}.png")

                        var uploadTask = file.putFile(u)
                        uploadTask.addOnFailureListener {
                            Log.d("image load", it.message ?: "error")
                        }.addOnSuccessListener { taskSnapshot ->
                            Toast.makeText(requireContext(), "Added", Toast.LENGTH_SHORT).show()
                            viewModel.getCurrentUser()?.uid?.let { viewModel.getPublicImages(it) }
                        }
                    }
                } else {
                    Log.d("PhotoPicker", "No media selected")
                }
            }

        binding.toolbar.trailingIconAction = {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    }
    override fun observeState() {
    }
}