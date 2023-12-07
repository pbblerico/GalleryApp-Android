package com.example.galleryapp.presentation.settings

import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.galleryapp.R
import com.example.galleryapp.base.BaseFragment
import com.example.galleryapp.databinding.FragmentSettingsBinding
import com.example.galleryapp.utils.enums.Languages
import com.google.firebase.auth.FirebaseAuth
import com.yariksoffice.lingver.Lingver
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentSettingsBinding>(R.layout.fragment_settings) {
    private val viewModel: SettingsViewModel by viewModels()
    override fun getViewBinding(): FragmentSettingsBinding = FragmentSettingsBinding.inflate(layoutInflater)

    override fun setUpViews() {
        val languages: MutableList<String> = mutableListOf()
        Languages.values().forEach {
            languages.add(getString(it.language))
        }

        binding.logout.isVisible = viewModel.loggedIn()
        binding.toolbar.trailingIconVisibility = !viewModel.loggedIn()

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, languages)
        binding.languagesList.adapter = adapter



        //todo сохранять язык
        binding.languagesList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when(position) {
                    0 -> updateLanguage(Languages.EN.id)
                    1 -> updateLanguage(Languages.RU.id)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}

        }


        binding.toolbar.trailingIconAction = {
            Navigation.findNavController(binding.root).navigate(R.id.loginFragment)
        }

        binding.logout.setOnClickListener {
            viewModel.logOut()
            activity?.recreate()
            Log.d("asdas", "${FirebaseAuth.getInstance().currentUser}")
        }
    }

    override fun observeState() {

    }

    private fun updateLanguage(lang: String) {
        val ling = Lingver.getInstance()
        if(ling.getLanguage() != lang) {
            ling.setLocale(requireContext(), lang)
            viewModel.saveLanguage(lang)
            activity?.recreate()
        }
    }
}