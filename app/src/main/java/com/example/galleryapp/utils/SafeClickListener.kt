package com.example.galleryapp.utils

import android.os.SystemClock
import android.view.View

class SafeClickListener(
    private var defaultInterval: Int = 1000,
    private val onSafeClick: (View) -> Unit
): View.OnClickListener {
    private var lastClickTime: Long = 0
    override fun onClick(view: View?) {
        if (SystemClock.elapsedRealtime() - lastClickTime < defaultInterval) {
            return
        }
        lastClickTime = SystemClock.elapsedRealtime()
        view?.let {
            onSafeClick(it)
        }
    }
}

fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}