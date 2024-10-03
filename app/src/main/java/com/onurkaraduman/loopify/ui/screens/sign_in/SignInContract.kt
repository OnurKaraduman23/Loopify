package com.onurkaraduman.loopify.ui.screens.sign_in

object SignInContract {

    data class SignInUiState(
        val email: String = "",
        val password: String = "",
    )

    sealed class SignInUiAction(){
        data object SignInClick: SignInUiAction()
        data class ChangeEmail(val email: String): SignInUiAction()
        data class ChangePassword(val password: String): SignInUiAction()
    }

    sealed class SignInUiEffect {
        data class ShowToast(val message: String): SignInUiEffect()
        data object GoToHomeScreen : SignInUiEffect()
        data object GoToSignUpScreen : SignInUiEffect()
    }
}