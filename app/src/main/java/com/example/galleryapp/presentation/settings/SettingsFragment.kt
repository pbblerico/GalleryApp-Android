package com.example.galleryapp.presentation.settings

import android.content.Context
import android.widget.ArrayAdapter
import androidx.navigation.Navigation
import com.example.galleryapp.R
import com.example.galleryapp.base.BaseFragment
import com.example.galleryapp.data.preferences.Preferences
import com.example.galleryapp.databinding.FragmentSettingsBinding
import com.example.galleryapp.utils.enums.Languages

class SettingsFragment : BaseFragment<FragmentSettingsBinding>(R.layout.fragment_settings) {
    override fun getViewBinding(): FragmentSettingsBinding = FragmentSettingsBinding.inflate(layoutInflater)

    override fun setUpViews() {
        val languages: MutableList<String> = mutableListOf()
        Languages.values().forEach {
            languages.add(getString(it.language))
        }

        val adapter = ArrayAdapter<String>(requireContext(), R.layout.fragment_settings, languages)
        binding.languagesList.setAdapter(adapter)

        binding.manageAccount.setOnClickListener {
            val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
            sharedPref.edit().putBoolean(Preferences.AUTHORIZED.name, true).apply()

        }

        binding.toolbar.trailingIconAction = {
            Navigation.findNavController(binding.root).navigate(R.id.loginFragment)
        }
    }

    override fun observeState() {

    }
}