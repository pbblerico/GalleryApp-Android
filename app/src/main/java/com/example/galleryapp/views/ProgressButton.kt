package com.example.galleryapp.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.example.galleryapp.R
import com.example.galleryapp.databinding.ViewProgressButtonBinding
import com.example.galleryapp.utils.setSafeOnClickListener
import com.google.android.material.button.MaterialButton

class ProgressButtonView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
): FrameLayout(context, attrs, defStyleAttr) {

    private val binding: ViewProgressButtonBinding =
        ViewProgressButtonBinding.inflate(LayoutInflater.from(context), this)

    private var loading = false
    var action: (() ->Unit)? = null
    val button: MaterialButton = binding.progressButton

    init {
        setAttrs(attrs, R.styleable.ProgressButtonView) {
            binding.progressButton.text = it.getString(R.styleable.ProgressButtonView_button_title)
        }

        binding.progressButton.setSafeOnClickListener { setLoading() }
    }


    fun setLoading() {
        loading = !loading
        if (loading) {
            action?.invoke()
            binding.progressAnimation.visibility = View.VISIBLE
            binding.progressButton.textScaleX = 0f
        } else {
            binding.progressAnimation.visibility = View.GONE
            binding.progressButton.textScaleX = 1f
        }
    }
}