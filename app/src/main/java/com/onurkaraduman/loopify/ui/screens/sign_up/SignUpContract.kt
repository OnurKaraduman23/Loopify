package com.onurkaraduman.loopify.ui.screens.sign_up

object SignUpContract {
    data class SingUpUiState(
        val isLoading: Boolean = false,
        val email: String = "",
        val password: String = "",
        val confirmPassword: String = "",
        val userName: String = ""
    )

    sealed class SignUpUiAction() {
        data object SignUpClick : SignUpUiAction()
        data class ChangeEmail(val email: String) : SignUpUiAction()
        data class ChangePassword(val password: String) : SignUpUiAction()
        data class ChangeConfirmPassword(val confirmPassword: String) : SignUpUiAction()
        data class ChangeUserName(val userName: String) : SignUpUiAction()

    }

    sealed class SingUpUiEffect {
        data class ShowToast(val message: String) : SingUpUiEffect()
        data object GoToHomeScreen : SingUpUiEffect()
    }
}