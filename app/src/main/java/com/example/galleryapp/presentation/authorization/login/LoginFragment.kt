package com.example.galleryapp.presentation.authorization.login

import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.galleryapp.R
import com.example.galleryapp.databinding.FragmentLoginBinding
import com.example.galleryapp.presentation.base.BaseFragment
import com.example.galleryapp.utils.setSafeOnClickListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment: BaseFragment<FragmentLoginBinding, LoginViewModel>(R.layout.fragment_login) {
    override fun getViewBinding(): FragmentLoginBinding = FragmentLoginBinding.inflate(layoutInflater)
    override fun getViewModelClass(): Class<LoginViewModel> = LoginViewModel::class.java

    override fun setUpViews() {
        binding.authorization.toolbar.startIconAction = {
            viewModel.handleEvent(LoginScreenContract.LoginEvent.OnBackIconClick)
        }

        binding.authorization.toOtherOption.setSafeOnClickListener {
            viewModel.handleEvent(LoginScreenContract.LoginEvent.OnSignUpClick)
        }

        binding.authorization.submitBtn.action = {
            login()
        }
    }

    private fun login() {
        val email = binding.authorization.emailET.text.toString().trim()
        val password = binding.authorization.passwordET.text.toString().trim()

        viewModel.handleEvent(LoginScreenContract.LoginEvent.OnLoginButtonClick(email, password))
    }

    override fun observeView() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect{state ->

            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.effect.collect{effect ->
                when(effect) {
                    is LoginScreenContract.LoginEffect.NavigateToSignUp -> {
                        Navigation.findNavController(binding.root).navigate(R.id.signUpFragment)
                    }
                    is LoginScreenContract.LoginEffect.NavigateBack -> {
                        Navigation.findNavController(binding.root).popBackStack()
                    }
                }
            }
        }
    }

}