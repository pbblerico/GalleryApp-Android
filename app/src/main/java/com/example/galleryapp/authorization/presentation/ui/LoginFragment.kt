package com.example.galleryapp.authorization.presentation.ui

import android.util.Log
import androidx.navigation.Navigation
import com.example.galleryapp.R
import com.example.galleryapp.authorization.presentation.viewModels.LoginViewModel
import com.example.galleryapp.databinding.FragmentLoginBinding
import com.example.galleryapp.shared.base.BaseFragment
import com.example.galleryapp.utils.setSafeOnClickListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment: BaseFragment<FragmentLoginBinding, LoginViewModel>(R.layout.fragment_login) {
    override fun getViewBinding(): FragmentLoginBinding = FragmentLoginBinding.inflate(layoutInflater)
    override fun getViewModelClass(): Class<LoginViewModel> = LoginViewModel::class.java

    override fun setUpViews() {
        Log.d("login_fragment", "set up")

        binding.authorization.toolbar.startIconAction = {
            Navigation.findNavController(binding.root).popBackStack()
        }

        binding.authorization.toOtherOption.setSafeOnClickListener {
            Navigation.findNavController(it).navigate(R.id.signUpFragment)
        }

        binding.authorization.submitBtn.action = {
            login()
        }
    }

    private fun login() {
        val email = binding.authorization.emailET.text.toString().trim()
        val password = binding.authorization.passwordET.text.toString().trim()

        Log.d("login_fragment", "hello")
//        viewModel.login(email, password)
    }

    override fun observeView() {
    }

}

//@AndroidEntryPoint
//class LoginFragment : Fragment() {
//    private lateinit var binding: FragmentLoginBinding
//    private val viewModel: LoginViewModel by viewModels()
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentLoginBinding.inflate(inflater, container, false)
//
//        binding.authorization.toolbar.startIconAction = {
//            Navigation.findNavController(binding.root).popBackStack()
//        }
//
//        binding.authorization.toOtherOption.setSafeOnClickListener {
//            Navigation.findNavController(it).navigate(R.id.signUpFragment)
//        }
//
//        return binding.root
//    }
//
//    private fun login() {
//        val email = binding.authorization.emailET.text.toString().trim()
//        val password = binding.authorization.passwordET.text.toString().trim()
//
//        viewModel.login(email, password)
//    }
//}