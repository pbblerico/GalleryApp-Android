package com.example.galleryapp.data.repositories.folder

import androidx.annotation.StringRes
import com.example.galleryapp.R

enum class FolderType(@StringRes val type: Int) {
    PRIVATE(R.string.private_enum),
    PUBLIC(R.string.public_enum)
}
