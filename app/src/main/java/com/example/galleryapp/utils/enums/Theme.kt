package com.example.galleryapp.utils.enums

import androidx.appcompat.app.AppCompatDelegate

enum class Theme(val system: Int) {
    LIGHT(AppCompatDelegate.MODE_NIGHT_NO),
    DARK(AppCompatDelegate.MODE_NIGHT_YES),
    SYSTEM(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
}