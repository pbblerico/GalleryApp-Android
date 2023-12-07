package com.example.galleryapp.presentation.settings

import android.util.Log
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.galleryapp.R
import com.example.galleryapp.base.BaseFragment
import com.example.galleryapp.databinding.FragmentSettingsBinding
import com.example.galleryapp.utils.enums.Languages
import com.example.galleryapp.utils.enums.Theme
import com.yariksoffice.lingver.Lingver
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentSettingsBinding>(R.layout.fragment_settings) {
    private val viewModel: SettingsViewModel by viewModels()
    override fun getViewBinding(): FragmentSettingsBinding =
        FragmentSettingsBinding.inflate(layoutInflater)

    override fun setUpViews() {


        binding.logout.isVisible = viewModel.loggedIn()
        binding.toolbar.trailingIconVisibility = !viewModel.loggedIn()

        binding.toolbar.trailingIconAction = {
            Navigation.findNavController(binding.root).navigate(R.id.loginFragment)
        }

        binding.logout.setOnClickListener {
            viewModel.logOut()
            activity?.recreate()
        }

        setLanguage()
        setTheme()
    }

    private fun setLanguage() {
//        val languages: MutableList<String> = mutableListOf()
//        Languages.values().forEach {
//            languages.add(getString(it.language))
//        }
//
//        val languages = resources.getStringArray(R.array.languages)
//
//
//        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, languages)
//        binding.autoCompleteLanguage.setAdapter(adapter)
//        binding.autoCompleteLanguage.setText("English")
//
//        //todo сохранять язык
//        binding.autoCompleteLanguage.onItemSelectedListener =
//            object : AdapterView.OnItemSelectedListener {
//                override fun onItemSelected(
//                    parentView: AdapterView<*>?,
//                    view: View?,
//                    position: Int,
//                    id: Long
//                ) {
//                    when (position) {
//                        0 -> updateLanguage(Languages.EN.id)
//                        1 -> updateLanguage(Languages.RU.id)
//                    }
//                }
//
//                override fun onNothingSelected(p0: AdapterView<*>?) {}
//
//            }

        val languages: MutableList<String> = mutableListOf()
        Languages.values().forEach {
            languages.add(getString(it.language))
        }

//        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, languages)
//        binding.languagesList.adapter = adapter
//
//
//
//        //todo сохранять язык
//        binding.languagesList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parentView: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                when(position) {
//                    0 -> updateLanguage(Languages.EN.id)
//                    1 -> updateLanguage(Languages.RU.id)
//                }
//            }
//
//            override fun onNothingSelected(p0: AdapterView<*>?) {}
//
//        }

    }


    private fun setTheme() {
        val themes: MutableList<String> = mutableListOf()
        Theme.values().forEach {
            themes.add(getString(it.type))
        }

        val adapter = ArrayAdapter(requireContext(), R.layout.item_text, themes)
        binding.autoCompleteTheme.setAdapter(adapter)
//
//        binding.theme.adapter = adapter
////
//        binding.theme.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parentView: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                Log.d("sad", "$id $position ")
//                if(viewModel.getCurrentTheme() != themes[position])
//
//                when(position) {
//                    0 -> updateTheme(Theme.LIGHT.system)
//                    1 -> updateTheme(Theme.DARK.system)
//                    2 -> updateTheme(Theme.SYSTEM.system)
//                }
//            }
//
//            override fun onNothingSelected(p0: AdapterView<*>?) {}
//
//        }
    }


    override fun observeState() {

    }

    private fun updateTheme(theme: Int) {
        val outValue = android.util.TypedValue()


        requireActivity().theme.resolveAttribute(android.R.attr.theme, outValue, true)
        Log.d("heh", "${outValue.resourceId}")

        if (theme != outValue.resourceId) {
            requireActivity().setTheme(theme)
            requireActivity().recreate()
        }
    }

    private fun updateLanguage(lang: String) {

        val currentLanguage = viewModel.getCurrentLanguage()
        val ling = Lingver.getInstance()
        if (currentLanguage != lang) {
            ling.setLocale(requireContext(), lang)
            viewModel.saveLanguage(lang)
            activity?.recreate()
            binding.autoCompleteLanguage.setText(lang)
        }
    }
}