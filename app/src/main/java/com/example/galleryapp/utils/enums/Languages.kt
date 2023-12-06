package com.example.galleryapp.utils.enums

import androidx.annotation.StringRes
import com.example.galleryapp.R

enum class Languages(@StringRes val language: Int, val id: String) {
    EN(R.string.english, "en"),
    RU(R.string.russian, "ru")
}