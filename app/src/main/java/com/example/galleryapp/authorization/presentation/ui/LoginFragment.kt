package com.example.galleryapp.authorization.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.galleryapp.R
import com.example.galleryapp.authorization.presentation.viewModels.LoginViewModel
import com.example.galleryapp.databinding.FragmentLoginBinding
import com.example.galleryapp.utils.setSafeOnClickListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.authorization.toolbar.startIconAction = {
            Navigation.findNavController(binding.root).popBackStack()
        }

        binding.authorization.toOtherOption.setSafeOnClickListener {
            Navigation.findNavController(it).navigate(R.id.signUpFragment)
        }

        return binding.root
    }

    private fun login() {
        val email = binding.authorization.emailET.text.toString().trim()
        val password = binding.authorization.passwordET.text.toString().trim()

        viewModel.login(email, password)
    }
}