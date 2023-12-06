package com.example.galleryapp.presentation.folder

import android.util.Log
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.galleryapp.R
import com.example.galleryapp.account.presentation.adapter.ImageAdapter
import com.example.galleryapp.base.BaseFragment
import com.example.galleryapp.data.models.Image
import com.example.galleryapp.databinding.FragmentFolderBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.storage
import dagger.hilt.android.AndroidEntryPoint
import java.util.UUID


@AndroidEntryPoint
class FolderFragment : BaseFragment<FragmentFolderBinding>(R.layout.fragment_folder) {
    private val adapter: ImageAdapter = ImageAdapter()
    override fun getViewBinding(): FragmentFolderBinding =
        FragmentFolderBinding.inflate(layoutInflater)

    override fun setUpViews() {

        binding.imageAdapter.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.imageAdapter.adapter = adapter

        Log.d("asd", "${FirebaseAuth.getInstance().currentUser?.uid}")

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


        val pickMedia =
            registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(10)) { uri ->
                // Callback is invoked after the user selects a media item or closes the
                // photo picker.
                if (uri != null) {
                    Log.d("PhotoPicker", "Selected URI: $uri")
//                uri.
                    val storageRef = Firebase.storage.reference

                    for(u in uri) {
                        val file =
                            storageRef.child("users/${FirebaseAuth.getInstance().currentUser?.uid}/images/public/{${UUID.randomUUID()}}.png")

                        var uploadTask = file.putFile(u)
                        uploadTask.addOnFailureListener {
                            // Handle unsuccessful uploads
                            Log.d("image load", it.message ?: "error")
                        }.addOnSuccessListener { taskSnapshot ->
                            // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
                            // ...
                            Toast.makeText(requireContext(), "Added", Toast.LENGTH_SHORT).show()
                        }
                    }
//                        val bitmap = (binding.image.drawable as BitmapDrawable).bitmap
//                        val baos = ByteArrayOutputStream()
//                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
//                        val data = baos.toByteArray()

//                        var uploadTask = file.putBytes(data)

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