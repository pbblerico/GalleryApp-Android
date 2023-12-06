package com.example.galleryapp.presentation.onBoarding

import com.example.galleryapp.base.TestBaseViewModel
import com.example.galleryapp.data.preferences.Preferences
import com.example.galleryapp.data.preferences.PreferencesUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val preferencesUtils: PreferencesUtils
): TestBaseViewModel<OnBoardingContract.OnBoardingState, OnBoardingContract.OnBoardingEvent, OnBoardingContract.OnBoardingEffect>() {
    override fun createInitialState(): OnBoardingContract.OnBoardingState =
        OnBoardingContract.OnBoardingState.Initial


    override fun handleEvent(event: OnBoardingContract.OnBoardingEvent) {
        when(event) {
            is OnBoardingContract.OnBoardingEvent.OnPageChanged -> {
                if(event.position in 0 until (event.lastIndex)) {
                    setEffect(OnBoardingContract.OnBoardingEffect.OnPageChanged(event.position))
                }
                else {
                    setEffect(OnBoardingContract.OnBoardingEffect.NavigateToHome)
                }
            }
        }
    }

    fun saveInitialEntry() {
        preferencesUtils.saveBoolean(Preferences.ONBOARDED, true)
    }

    fun getInitialEntry(): Boolean {
        return preferencesUtils.getBoolean(Preferences.ONBOARDED, false)
    }

}