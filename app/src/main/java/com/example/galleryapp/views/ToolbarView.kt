package com.example.galleryapp.views

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.isVisible
import com.example.galleryapp.R
import com.example.galleryapp.databinding.ViewToolbarBinding
import com.example.galleryapp.utils.setSafeOnClickListener

class ToolbarView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
): FrameLayout(context, attrs, defStyleAttr) {

    private val binding: ViewToolbarBinding =
        ViewToolbarBinding.inflate(LayoutInflater.from(context), this)

    var startIconAction: (() -> Unit)? = null
    var trailingIconAction: (() -> Unit)? = null

    init {
        setAttrs(attrs, R.styleable.ToolbarView) {
            binding.toolbarTitle.text = it.getString(R.styleable.ToolbarView_toolbar_title)
            binding.startIcon.setImageResource(
                it.getResourceId(R.styleable.ToolbarView_start_icon, R.drawable.ic_arrow_back)
            )
            binding.startIcon.isVisible = it.getBoolean(R.styleable.ToolbarView_start_icon_visibility, false)

            binding.trailingIcon.setImageResource(
                it.getResourceId(R.styleable.ToolbarView_trailing_icon, R.drawable.ic_login)
            )
            binding.trailingIcon.isVisible = it.getBoolean(R.styleable.ToolbarView_trailing_icon_visibility, false)
        }

        binding.startIcon.setSafeOnClickListener {
            startIconAction?.invoke()
        }

        binding.trailingIcon.setSafeOnClickListener {
            trailingIconAction?.invoke()
        }
    }
}

inline fun View.setAttrs(
    attrs: AttributeSet?,
    styleable: IntArray,
    crossinline body: (TypedArray) -> Unit
) {
    context.theme.obtainStyledAttributes(attrs, styleable, 0, 0)
        .apply {
            try {
                body.invoke(this)
            } finally {
                recycle()
            }
        }
}