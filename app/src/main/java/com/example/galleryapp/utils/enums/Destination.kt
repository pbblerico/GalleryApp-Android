package com.example.galleryapp.utils.enums

import androidx.annotation.IdRes
import com.example.galleryapp.R

enum class Destination(@IdRes val fragmentId: Int) {
//    FOLDER(R.id.folderFragment),
    ACCOUNT(R.id.accountFragment),
//    LOGIN(R.id.loginFragment),
//    SIGN_UP(R.id.signUpFragment),
//    ONBOARDING(R.id.onBoardingFragment),
    HOME(R.id.homeFragment),
    SEARCH(R.id.searchFragment),
    SETTINGS(R.id.settingsFragment)
}