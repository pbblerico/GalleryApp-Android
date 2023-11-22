package com.example.galleryapp.data.modules

import com.example.galleryapp.viewModels.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        LoginViewModel(
            repo = get()
        )
    }
}