package com.example.galleryapp.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.galleryapp.R
import com.example.galleryapp.databinding.ViewAvatarImageBinding

class AvatarImageView  @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
): FrameLayout(context, attrs, defStyleAttr) {

    private val binding = ViewAvatarImageBinding.inflate(LayoutInflater.from(context), this)

    init {
        setAttrs(attrs, R.styleable.AvatarImageView) {
            binding.imageView.setImageResource(
                it.getResourceId(R.styleable.AvatarImageView_image, R.drawable.anonymous)
            )
            binding.nickname.text = it.getString(R.styleable.AvatarImageView_text)
        }
    }
}