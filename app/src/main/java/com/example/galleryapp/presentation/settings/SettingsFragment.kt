package com.example.galleryapp.presentation.settings

import android.content.Context
import android.view.View
import android.widget.AdapterView
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

        val adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, languages)
        binding.languagesList.adapter = adapter

        binding.languagesList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}

        }

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