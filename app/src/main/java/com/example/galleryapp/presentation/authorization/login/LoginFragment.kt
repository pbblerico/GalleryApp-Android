package com.example.galleryapp.presentation.authorization.login

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.galleryapp.R
import com.example.galleryapp.base.BaseFragment
import com.example.galleryapp.databinding.FragmentLoginBinding
import com.example.galleryapp.utils.setSafeOnClickListener
import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment: BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModels()
    override fun getViewBinding(): FragmentLoginBinding = FragmentLoginBinding.inflate(layoutInflater)

    override fun setUpViews() {
        binding.authorization.toolbar.startIconAction = {
            viewModel.handleEvent(AuthContract.AuthEvent.OnBackIconClick)
        }

        binding.authorization.toOtherOption.setSafeOnClickListener {
            viewModel.handleEvent(AuthContract.AuthEvent.OnSignUpClick)
        }

        binding.authorization.submitBtn.action = {
            login()
        }
    }

    private fun login() {
        val email = binding.authorization.emailET.text.toString().trim()
        val password = binding.authorization.passwordET.text.toString().trim()

        viewModel.handleEvent(AuthContract.AuthEvent.OnAuthButtonClick(email, password))
    }

    override fun observeState() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect{state ->
                when(state) {
                    is AuthContract.AuthState.Success -> {
                        Log.d("login_state", "hello")

                        val storageRef = Firebase.storage.reference
                        Navigation.findNavController(binding.root).navigate(R.id.homeFragment)

//                        val file = storageRef.child("users/${viewModel.loginUseCase.getCurrentUser()?.uid}/images/public/first.png")
//                        val bitmap = (binding.image.drawable as BitmapDrawable).bitmap
//                        val baos = ByteArrayOutputStream()
//                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
//                        val data = baos.toByteArray()

//                        var uploadTask = file.putBytes(data)
//                        uploadTask.addOnFailureListener {
//                            // Handle unsuccessful uploads
//                            Log.d("image load", it.message ?: "error")
//                        }.addOnSuccessListener { taskSnapshot ->
//                            // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
//                            // ...
//                            Toast.makeText(requireContext(), "Added", Toast.LENGTH_SHORT).show()
//                        }
                    }
                    else -> {}
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.effect.collect{effect ->
                when(effect) {
                    is AuthContract.AuthEffect.NavigateToAnotherAuthMethod -> {
                        Navigation.findNavController(binding.root).navigate(R.id.signUpFragment)
                    }
                    is AuthContract.AuthEffect.NavigateBack -> {
                        Navigation.findNavController(binding.root).popBackStack()
                    }
                    is AuthContract.AuthEffect.ShowToast -> {
                        Toast.makeText(requireContext(), effect.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

}