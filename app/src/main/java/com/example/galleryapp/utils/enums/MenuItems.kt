package com.example.galleryapp.utils.enums

import androidx.annotation.IdRes
import com.example.galleryapp.R

enum class MenuItems(@IdRes val fragmentId: Int) {
    ACCOUNT(R.id.accountFragment),
    HOME(R.id.homeFragment),
    SEARCH(R.id.searchFragment),
    SETTINGS(R.id.settingsFragment)
}