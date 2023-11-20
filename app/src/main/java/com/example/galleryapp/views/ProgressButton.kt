package com.example.galleryapp.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.example.galleryapp.databinding.ViewProgressButtonBinding
import com.example.galleryapp.utils.setSafeOnClickListener

class ProgressButtonView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
): FrameLayout(context, attrs, defStyleAttr) {

    private val binding: ViewProgressButtonBinding =
        ViewProgressButtonBinding.inflate(LayoutInflater.from(context), this)


    init {
        binding.progressButton.setSafeOnClickListener { setLoading(false) }
    }


    fun setLoading(loading: Boolean) {
        isClickable = !loading
        if (loading) {
            binding.progressAnimation.visibility = View.VISIBLE
            binding.progressButton.textScaleX = 0f
        } else {
            binding.progressAnimation.visibility = View.GONE
            binding.progressButton.textScaleX = 1f
        }
    }
}