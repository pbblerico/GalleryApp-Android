package com.example.galleryapp.presentation.onBoarding

import android.content.Context
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import com.example.galleryapp.R
import com.example.galleryapp.account.presentation.adapter.ViewPagerAdapter
import com.example.galleryapp.base.BaseFragment
import com.example.galleryapp.data.preferences.PreferencesUtils
import com.example.galleryapp.databinding.FragmentOnBoardingBinding
import com.example.galleryapp.utils.enums.OnBoardingElement
import com.google.android.material.tabs.TabLayoutMediator


class OnBoardingFragment : BaseFragment<FragmentOnBoardingBinding>(R.layout.fragment_on_boarding) {
    override fun getViewBinding(): FragmentOnBoardingBinding = FragmentOnBoardingBinding.inflate(layoutInflater)

    override fun setUpViews() {
        val fragmentList: MutableList<Fragment> = mutableListOf()
        OnBoardingElement.values().forEach {
            fragmentList.add(OnBoardingItemFragment(it))
        }


        val adapter = ViewPagerAdapter(parentFragmentManager, lifecycle, fragmentList)
        binding.viewPager.adapter = adapter

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                setButtons(position == 0, position == fragmentList.size - 1)
            }
        })


        binding.previousButton.setOnClickListener {
            if(binding.viewPager.currentItem > 0) {
                binding.viewPager.setCurrentItem(binding.viewPager.currentItem - 1, true)
            }
        }

        binding.viewPager.isUserInputEnabled = false

        TabLayoutMediator(binding.bottomTab, binding.viewPager) {tab, _ ->
            tab.view.isClickable = false
        }.attach()

        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val sharedUtils = PreferencesUtils(sharedPref)

        binding.nextButton.setOnClickListener {
            if(binding.viewPager.currentItem < fragmentList.size - 1) {
                binding.viewPager.setCurrentItem(binding.viewPager.currentItem + 1, true)
            } else {
                sharedPref.edit().putBoolean("entered", true).apply()
                Navigation.findNavController(it).navigate(R.id.homeFragment)

            }
        }

        if(sharedPref.getBoolean("entered", false)) {
            Navigation.findNavController(requireView()).navigate(R.id.homeFragment)
        }
    }

    override fun observeState() {
        TODO("Not yet implemented")
    }

    fun setButtons(firstPage: Boolean, lastPage: Boolean) {
        binding.previousButton.isClickable = !firstPage
        binding.previousButton.isVisible = !firstPage
        binding.nextButton.text = if (lastPage) "Done" else "Next"
    }
}