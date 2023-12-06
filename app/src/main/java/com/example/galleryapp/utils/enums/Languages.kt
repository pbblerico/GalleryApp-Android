package com.example.galleryapp.utils.enums

import androidx.annotation.StringRes
import com.example.galleryapp.R

enum class Languages(@StringRes val language: Int) {
    EN(R.string.english),
    RU(R.string.russian)
}