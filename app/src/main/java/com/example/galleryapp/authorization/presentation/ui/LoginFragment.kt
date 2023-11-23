package com.example.galleryapp.authorization.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.galleryapp.R
import com.example.galleryapp.databinding.FragmentLoginBinding
import com.example.galleryapp.utils.setSafeOnClickListener
import com.example.galleryapp.authorization.presentation.viewModels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
//    private val viewModel: LoginViewModel by viewModel()

    private val viewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.button.action = {
            viewModel.login(binding.email.text.toString(), binding.password.text.toString())
        }

        binding.toSignUp.setSafeOnClickListener {
            Navigation.findNavController(it).navigate(R.id.signUpFragment)
        }

        return binding.root
    }

}