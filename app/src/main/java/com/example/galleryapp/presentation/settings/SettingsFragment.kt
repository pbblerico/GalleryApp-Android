package com.example.galleryapp.presentation.settings

import com.example.galleryapp.R
import com.example.galleryapp.databinding.FragmentSettingsBinding
import com.example.galleryapp.presentation.base.BaseFragment

class SettingsFragment : BaseFragment<FragmentSettingsBinding, SettingsViewModel>(R.layout.fragment_settings) {
    override fun getViewBinding(): FragmentSettingsBinding = FragmentSettingsBinding.inflate(layoutInflater)
    override fun getViewModelClass(): Class<SettingsViewModel> = SettingsViewModel::class.java

    override fun setUpViews() {
        TODO("Not yet implemented")
    }

    override fun observeView() {
        TODO("Not yet implemented")
    }
}