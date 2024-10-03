package com.onurkaraduman.loopify.ui.screens.splash

object SplashContract {
    sealed class SplashUiEffect {
        data class ShowToast(val message: String): SplashUiEffect()
        data object GoToMainScreen : SplashUiEffect()
        data object GoToSignInScreen : SplashUiEffect()
    }
}