package com.example.galleryapp.presentation.authorization.login

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.galleryapp.R
import com.example.galleryapp.databinding.FragmentLoginBinding
import com.example.galleryapp.presentation.base.BaseFragment
import com.example.galleryapp.utils.setSafeOnClickListener
import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import dagger.hilt.android.AndroidEntryPoint
import java.io.ByteArrayOutputStream


@AndroidEntryPoint
class LoginFragment: BaseFragment<FragmentLoginBinding, LoginViewModel>(R.layout.fragment_login) {
    override fun getViewBinding(): FragmentLoginBinding = FragmentLoginBinding.inflate(layoutInflater)
    override fun getViewModelClass(): Class<LoginViewModel> = LoginViewModel::class.java

    override fun setUpViews() {
        binding.authorization.toolbar.startIconAction = {
            viewModel.handleEvent(AuthScreenContract.AuthEvent.OnBackIconClick)
        }

        binding.authorization.toOtherOption.setSafeOnClickListener {
            viewModel.handleEvent(AuthScreenContract.AuthEvent.OnSignUpClick)
        }

        binding.authorization.submitBtn.action = {
            login()
        }
    }

    private fun login() {
        val email = binding.authorization.emailET.text.toString().trim()
        val password = binding.authorization.passwordET.text.toString().trim()

        viewModel.handleEvent(AuthScreenContract.AuthEvent.OnAuthButtonClick(email, password))
    }

    override fun observeState() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect{state ->
                when(state.authState) {
                    is AuthScreenContract.AuthState.Success -> {
                        Log.d("login_state", "hello")

                        val storageRef = Firebase.storage.reference

                        val file = storageRef.child("users/${viewModel.loginUseCase.getCurrentUser()?.uid}/images/public/first.png")
                        val bitmap = (binding.image.drawable as BitmapDrawable).bitmap
                        val baos = ByteArrayOutputStream()
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                        val data = baos.toByteArray()

                        var uploadTask = file.putBytes(data)
                        uploadTask.addOnFailureListener {
                            // Handle unsuccessful uploads
                            Log.d("image load", it.message ?: "error")
                        }.addOnSuccessListener { taskSnapshot ->
                            // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
                            // ...
                            Toast.makeText(requireContext(), "Added", Toast.LENGTH_SHORT).show()
                        }
                    }
                    else -> {}
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.effect.collect{effect ->
                when(effect) {
                    is AuthScreenContract.AuthEffect.NavigateToAnotherAuthMethod -> {
                        Navigation.findNavController(binding.root).navigate(R.id.signUpFragment)
                    }
                    is AuthScreenContract.AuthEffect.NavigateBack -> {
                        Navigation.findNavController(binding.root).popBackStack()
                    }
                    is AuthScreenContract.AuthEffect.ShowToast -> {
                        Toast.makeText(requireContext(), effect.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

}