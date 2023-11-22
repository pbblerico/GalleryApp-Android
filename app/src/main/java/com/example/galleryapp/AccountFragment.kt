package com.example.galleryapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet.Motion
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.galleryapp.databinding.FragmentAccountBinding
import com.example.galleryapp.utils.setSafeOnClickListener


class AccountFragment : Fragment() {
    private lateinit var binding: FragmentAccountBinding

    private var isShow = true
    private var scrollRange = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountBinding.inflate(inflater, container, false)
//        binding.toolBar.inflateMenu(R.menu.account_app_bar_logged_user)


        binding.toolbar.trailingIconAction = {
//            Toast.makeText(requireContext(), "${binding.toolbar.scrollY}", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(binding.root).navigate(R.id.loginFragment)
        }

//        binding.toolbar.setSafeOnClickListener {
//            if
//        }

        return binding.root
    }

    private fun blockToolbar(motion: MotionEvent): Boolean {
        return false
    }
}