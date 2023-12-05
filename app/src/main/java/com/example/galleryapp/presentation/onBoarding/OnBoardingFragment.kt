package com.example.galleryapp.presentation.onBoarding

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import com.example.galleryapp.R
import com.example.galleryapp.account.presentation.adapter.ViewPagerAdapter
import com.example.galleryapp.databinding.FragmentOnBoardingBinding
import com.example.galleryapp.utils.enums.OnBoardingElement
import com.google.android.material.tabs.TabLayoutMediator


class OnBoardingFragment : Fragment() {
    private lateinit var binding: FragmentOnBoardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)


        val fragmentList: MutableList<Fragment> = mutableListOf()
        OnBoardingElement.values().forEach {
            fragmentList.add(OnBoardingItemFragment(it))
        }
//        Log.d("fragmet", fragmentList.size.toString())

        val adapter = ViewPagerAdapter(parentFragmentManager, lifecycle, fragmentList)
        binding.viewPager.adapter = adapter

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                setButtons(position == 0, position == fragmentList.size - 1)
            }

        })

        binding.nextButton.setOnClickListener {
            if(binding.viewPager.currentItem < fragmentList.size - 1) {
                binding.viewPager.setCurrentItem(binding.viewPager.currentItem + 1, true)
            } else {
                Navigation.findNavController(it).navigate(R.id.homeFragment)

            }
        }
        binding.previousButton.setOnClickListener {
            if(binding.viewPager.currentItem > 0) {
                binding.viewPager.setCurrentItem(binding.viewPager.currentItem - 1, true)
            }
        }

        binding.viewPager.isUserInputEnabled = false

        Log.d("asd", binding.viewPager.currentItem.toString())

        TabLayoutMediator(binding.bottomTab, binding.viewPager) {tab, _ ->
            tab.view.isClickable = false
        }.attach()

        return binding.root
    }

    fun setButtons(firstPage: Boolean, lastPage: Boolean) {
        binding.previousButton.isClickable = !firstPage
        binding.previousButton.isVisible = !firstPage
        binding.nextButton.text = if (lastPage) "Done" else "Next"
    }
}