package com.example.galleryapp.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.view.isVisible
import com.example.galleryapp.R
import com.example.galleryapp.databinding.ViewAuthorizationBinding

class AuthorizationView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
): FrameLayout(context, attrs, defStyleAttr)   {

    private val binding = ViewAuthorizationBinding.inflate(LayoutInflater.from(context), this)

    val nicknameET = binding.nickname
    val emailET = binding.email
    val passwordET = binding.password
    val submitBtn = binding.submitButton
    val toOtherOption = binding.toOtherOption
    val toolbar = binding.toolbar

    init {
        setAttrs(attrs, R.styleable.AuthorizationView) { it ->
            binding.nickname.isVisible = it.getBoolean(R.styleable.AuthorizationView_show_nickname_input, false)
            //todo(make it enum)
            val title =  it.getString(R.styleable.AuthorizationView_authorization_title)?.uppercase()
            binding.authorizationTitle.text = title
            binding.submitButton.button.text = title

            var toOtherOption = "to "
            title?.let {
                toOtherOption += if(it.lowercase() == "login") "sign up"
                else "login"
            }
            binding.toOtherOption.text = toOtherOption
        }
    }
}