package com.example.galleryapp.utils.enums

import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatDelegate
import com.example.galleryapp.R

enum class Theme(val system: Int, @StringRes val type: Int) {
    LIGHT(AppCompatDelegate.MODE_NIGHT_NO, R.string.light_theme),
    DARK(AppCompatDelegate.MODE_NIGHT_YES, R.string.dark_theme),
    SYSTEM(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM, R.string.system_theme)
}