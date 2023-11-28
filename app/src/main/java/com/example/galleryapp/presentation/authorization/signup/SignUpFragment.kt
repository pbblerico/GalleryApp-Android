package com.example.galleryapp.presentation.authorization.signup

import androidx.navigation.Navigation
import com.example.galleryapp.R
import com.example.galleryapp.databinding.FragmentSignUpBinding
import com.example.galleryapp.presentation.base.BaseFragment
import com.example.galleryapp.utils.setSafeOnClickListener
import dagger.hilt.android.AndroidEntryPoint

//import org.koin.androidx.viewmodel.ext.android.viewModel

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding, SignUpViewModel>(R.layout.fragment_sign_up) {

    //    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentSignUpBinding.inflate(inflater, container, false)
////        binding.button.action = {
////            viewModel.signUp(binding.nickname.text.toString(), binding.email.text.toString(), binding.password.text.toString())
////        }
//
//        return binding.root
//    }
    override fun getViewBinding() = FragmentSignUpBinding.inflate(layoutInflater)

    override fun getViewModelClass() = SignUpViewModel::class.java

    override fun setUpViews() {
        binding.authorization.toOtherOption.setSafeOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.loginFragment)
        }
    }

    override fun observeView() {
    }
}