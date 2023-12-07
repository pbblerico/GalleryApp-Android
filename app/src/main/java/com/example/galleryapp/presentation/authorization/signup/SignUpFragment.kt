package com.example.galleryapp.presentation.authorization.signup

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.galleryapp.R
import com.example.galleryapp.base.BaseFragment
import com.example.galleryapp.databinding.FragmentSignUpBinding
import com.example.galleryapp.presentation.authorization.login.AuthContract
import com.example.galleryapp.utils.enums.Destination
import com.example.galleryapp.utils.setSafeOnClickListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding>(R.layout.fragment_sign_up) {
    private val viewModel: SignUpViewModel by viewModels()
    override fun getViewBinding() = FragmentSignUpBinding.inflate(layoutInflater)

    override fun setUpViews() {
        binding.authorization.toolbar.startIconAction = {
            viewModel.handleEvent(AuthContract.AuthEvent.OnBackIconClick)
        }

        binding.authorization.toOtherOption.setSafeOnClickListener {
            viewModel.handleEvent(AuthContract.AuthEvent.OnSignUpClick)
        }

        binding.authorization.submitBtn.action = {
            signUp()
        }
    }

    override fun observeState() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { state ->
                when (state) {
                    is AuthContract.AuthState.Success -> {
                        binding.authorization.submitBtn.isClickable = true
                        viewModel.saveLogs()
                        requireActivity().recreate()
                    }
                    is AuthContract.AuthState.Loading -> {
                        binding.authorization.submitBtn.isClickable = false
                    }
                    is AuthContract.AuthState.Failure -> {
                        Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> {}
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.effect.collect { effect ->
                when (effect) {
                    is AuthContract.AuthEffect.NavigateToAnotherAuthMethod -> {
                        Navigation.findNavController(binding.root).navigate(R.id.loginFragment)
                    }

                    is AuthContract.AuthEffect.NavigateBack -> {
                        Navigation.findNavController(binding.root).popBackStack()
                    }

                    is AuthContract.AuthEffect.ShowToast -> {
                        Toast.makeText(requireContext(), effect.message, Toast.LENGTH_SHORT).show()
                    }
                    is AuthContract.AuthEffect.NavigateToHome -> {
                        Navigation.findNavController(binding.root).navigate(Destination.HOME.fragmentId)
                    }
                }
            }
        }
    }

    private fun signUp() {
        val email = binding.authorization.emailET.text.toString().trim()
        val password = binding.authorization.passwordET.text.toString().trim()
        val nickname = binding.authorization.nicknameET.text.toString().trim()

        viewModel.handleEvent(
            AuthContract.AuthEvent.OnAuthButtonClick(
                email,
                password,
                nickname
            )
        )

    }
}