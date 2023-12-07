package com.example.galleryapp.presentation.settings

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.navigation.Navigation
import com.example.galleryapp.R
import com.example.galleryapp.base.BaseFragment
import com.example.galleryapp.databinding.FragmentSettingsBinding
import com.example.galleryapp.utils.enums.Languages
import com.yariksoffice.lingver.Lingver

class SettingsFragment : BaseFragment<FragmentSettingsBinding>(R.layout.fragment_settings) {
    override fun getViewBinding(): FragmentSettingsBinding = FragmentSettingsBinding.inflate(layoutInflater)

    override fun setUpViews() {
        val languages: MutableList<String> = mutableListOf()
        Languages.values().forEach {
            languages.add(getString(it.language))
        }

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, languages)
        binding.languagesList.adapter = adapter


        binding.languagesList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var lanToUpdate: String? = null
                when(position) {
                    0 -> updateLanguage(Languages.EN.id)
                    1 -> updateLanguage(Languages.RU.id)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}

        }

//        binding.manageAccount.setOnClickListener {
//            val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
//            sharedPref.edit().putBoolean(Preferences.AUTHORIZED.name, true).apply()
//
//        }

        binding.toolbar.trailingIconAction = {
            Navigation.findNavController(binding.root).navigate(R.id.loginFragment)
        }
    }

    override fun observeState() {

    }

    private fun updateLanguage(lang: String) {
        val ling = Lingver.getInstance()
        if(ling.getLanguage() != lang) {
            ling.setLocale(requireContext(), lang)
            activity?.recreate()
        }

//        resources.updateConfiguration(Configuration(), resources.displayMetrics)


    }
}