package com.example.galleryapp.presentation.onBoarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.galleryapp.databinding.FragmentOnBoardingItemBinding
import com.example.galleryapp.utils.enums.OnBoardingElement

class OnBoardingItemFragment(private val onBoardingElement: OnBoardingElement?): Fragment() {
    private lateinit var binding: FragmentOnBoardingItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardingItemBinding.inflate(inflater, container, false)

        onBoardingElement?.let {
            binding.text.text = getString(it.text)
            binding.animation.setAnimation(it.animation)
        }

        return binding.root
    }



}