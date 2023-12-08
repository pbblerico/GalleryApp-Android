package com.example.galleryapp.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.example.galleryapp.R
import com.example.galleryapp.databinding.ViewProgressButtonBinding
import com.example.galleryapp.utils.setSafeOnClickListener

class ProgressButtonView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
): FrameLayout(context, attrs, defStyleAttr) {

    private val binding: ViewProgressButtonBinding =
        ViewProgressButtonBinding.inflate(LayoutInflater.from(context), this)

    var action: (() ->Unit)? = null

    var buttonTitle: String
        get() = binding.progressButton.text.toString()
        set(value) {
            binding.progressButton.text = value
        }

    init {
        setAttrs(attrs, R.styleable.ProgressButtonView) {
            binding.progressButton.text = it.getString(R.styleable.ProgressButtonView_button_title)
        }

        binding.progressButton.setSafeOnClickListener { action?.invoke() }
    }


    fun setLoading(loading: Boolean) {
        binding.progressAnimation.visibility = if (loading) View.VISIBLE else View.GONE
        binding.progressButton.isEnabled = !loading
        binding.progressButton.textScaleX = if (loading) 0f else 1f
    }
}