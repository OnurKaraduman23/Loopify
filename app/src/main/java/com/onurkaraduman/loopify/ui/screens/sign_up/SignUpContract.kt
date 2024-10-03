package com.onurkaraduman.loopify.ui.screens.sign_up

object SignUpContract {
    data class SingUpUiState(
        val isLoading: Boolean = false,
        val email: String = "",
        val password: String = "",
    )

    sealed class SingUpUiAction(){
        data object SignUpClick: SingUpUiAction()
        data class ChangeEmail(val email: String): SingUpUiAction()
        data class ChangePassword(val password: String): SingUpUiAction()
    }

    sealed class SingUpUiEffect {
        data class ShowToast(val message: String): SingUpUiEffect()
        data object GoToHomeScreen : SingUpUiEffect()
    }
}