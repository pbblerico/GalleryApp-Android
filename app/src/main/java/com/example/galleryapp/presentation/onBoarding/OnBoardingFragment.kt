package com.example.galleryapp.presentation.onBoarding

import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import com.example.galleryapp.R
import com.example.galleryapp.account.presentation.adapter.ViewPagerAdapter
import com.example.galleryapp.base.BaseFragment
import com.example.galleryapp.databinding.FragmentOnBoardingBinding
import com.example.galleryapp.utils.enums.Destination
import com.example.galleryapp.utils.enums.OnBoardingElement
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class OnBoardingFragment : BaseFragment<com.example.galleryapp.databinding.FragmentOnBoardingBinding>(R.layout.fragment_on_boarding) {
    private val viewModel: OnBoardingViewModel by viewModels()
    override fun getViewBinding(): FragmentOnBoardingBinding =
        FragmentOnBoardingBinding.inflate(layoutInflater)

    override fun setUpViews() {
        if (viewModel.getInitialEntry()) {
            Navigation.findNavController(requireView()).navigate(R.id.homeFragment)
        }

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

        binding.viewPager.isUserInputEnabled = false

        TabLayoutMediator(binding.bottomTab, binding.viewPager) { tab, _ ->
            tab.view.isClickable = false
        }.attach()


        binding.previousButton.setOnClickListener {
            viewModel.handleEvent(
                OnBoardingContract.OnBoardingEvent.OnPageChanged(
                    binding.viewPager.currentItem - 1,
                    fragmentList.size
                )
            )
        }
        binding.nextButton.setOnClickListener {
            viewModel.handleEvent(
                OnBoardingContract.OnBoardingEvent.OnPageChanged(
                    binding.viewPager.currentItem + 1,
                    fragmentList.size
                )
            )
        }

    }

    override fun observeState() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.effect.collect { effect ->
                when (effect) {
                    is OnBoardingContract.OnBoardingEffect.OnPageChanged -> {
                        binding.viewPager.setCurrentItem(effect.position, true)
                    }

                    is OnBoardingContract.OnBoardingEffect.NavigateToHome -> {
                        viewModel.saveInitialEntry()
                        Navigation.findNavController(binding.root)
                            .navigate(Destination.HOME.fragmentId)
                    }
                }
            }
        }
    }

    fun setButtons(firstPage: Boolean, lastPage: Boolean) {
        binding.previousButton.isClickable = !firstPage
        binding.previousButton.isVisible = !firstPage
        binding.nextButton.text = if (lastPage) "Done" else "Next"
    }
}