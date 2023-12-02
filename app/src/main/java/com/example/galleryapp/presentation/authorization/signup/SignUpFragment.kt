package com.example.galleryapp.presentation.authorization.signup

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.galleryapp.R
import com.example.galleryapp.databinding.FragmentSignUpBinding
import com.example.galleryapp.presentation.authorization.login.AuthScreenContract
import com.example.galleryapp.base.BaseFragment
import com.example.galleryapp.utils.setSafeOnClickListener
import dagger.hilt.android.AndroidEntryPoint

//import org.koin.androidx.viewmodel.ext.android.viewModel

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding, SignUpViewModel>(R.layout.fragment_sign_up) {

    override fun getViewBinding() = FragmentSignUpBinding.inflate(layoutInflater)

    override fun getViewModelClass() = SignUpViewModel::class.java

    override fun setUpViews() {
        binding.authorization.toolbar.startIconAction = {
            viewModel.handleEvent(AuthScreenContract.AuthEvent.OnBackIconClick)
        }

        binding.authorization.toOtherOption.setSafeOnClickListener {
            viewModel.handleEvent(AuthScreenContract.AuthEvent.OnSignUpClick)
        }

        binding.authorization.submitBtn.action = {
            signUp()
        }
    }

    override fun observeState() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect{state ->
                when(state.authState) {
                    is AuthScreenContract.AuthState.Success -> {
                        Log.d("sign_state", "hello")
                    }
                    else -> {}
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.effect.collect{effect ->
                when(effect) {
                    is AuthScreenContract.AuthEffect.NavigateToAnotherAuthMethod -> {
                        Navigation.findNavController(binding.root).navigate(R.id.loginFragment)
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

    private fun signUp() {
        val email = binding.authorization.emailET.text.toString().trim()
        val password = binding.authorization.passwordET.text.toString().trim()
        val nickname = binding.authorization.nicknameET.text.toString().trim()

        viewModel.handleEvent(AuthScreenContract.AuthEvent.OnAuthButtonClick(email, password, nickname))

    }
}