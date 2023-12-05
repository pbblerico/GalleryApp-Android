package com.example.galleryapp.presentation.settings

import com.example.galleryapp.R
import com.example.galleryapp.base.BaseFragment
import com.example.galleryapp.databinding.FragmentSettingsBinding

class SettingsFragment : BaseFragment<FragmentSettingsBinding, SettingsViewModel>(R.layout.fragment_settings) {
    override fun getViewBinding(): FragmentSettingsBinding = FragmentSettingsBinding.inflate(layoutInflater)
    override fun getViewModelClass(): Class<SettingsViewModel> = SettingsViewModel::class.java

    override fun setUpViews() {

    }

    override fun observeState() {

    }
}