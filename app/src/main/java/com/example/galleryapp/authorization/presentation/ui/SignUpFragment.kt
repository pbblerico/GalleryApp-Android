package com.example.galleryapp.authorization.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.galleryapp.databinding.FragmentSignUpBinding

//import org.koin.androidx.viewmodel.ext.android.viewModel


class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
//    private val viewModel: LoginViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
//        binding.button.action = {
//            viewModel.signUp(binding.nickname.text.toString(), binding.email.text.toString(), binding.password.text.toString())
//        }

        return binding.root
    }
}