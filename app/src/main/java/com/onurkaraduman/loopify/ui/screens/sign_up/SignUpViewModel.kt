package com.onurkaraduman.loopify.ui.screens.sign_up

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onurkaraduman.loopify.common.Resource
import com.onurkaraduman.loopify.data.repository.auth_repository.AuthRepository
import com.onurkaraduman.loopify.ui.screens.sign_up.SignUpContract.SingUpUiAction
import com.onurkaraduman.loopify.ui.screens.sign_up.SignUpContract.SingUpUiEffect
import com.onurkaraduman.loopify.ui.screens.sign_up.SignUpContract.SingUpUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository
):ViewModel() {

    private val _signUpUiState = MutableStateFlow(SingUpUiState())
    val signUpUiState: StateFlow<SingUpUiState> = _signUpUiState.asStateFlow()

    private val _signUpUiEffect by lazy { Channel<SingUpUiEffect>() }
    val signUpUiEffect: Flow<SingUpUiEffect> by lazy { _signUpUiEffect.receiveAsFlow() }


    fun onAction(uiAction: SingUpUiAction) {
        when (uiAction) {
            is SingUpUiAction.SignUpClick -> signUp()
            is SingUpUiAction.ChangeEmail -> updateUiState { copy(email = uiAction.email) }
            is SingUpUiAction.ChangePassword -> updateUiState { copy(password = uiAction.password) }
        }
    }


    private fun signUp() = viewModelScope.launch {
        when(val result = authRepository.signUp(signUpUiState.value.email, signUpUiState.value.password)) {
            is Resource.Success -> {
                updateUiState { copy(isLoading = false) }
                emitUiEffect(SingUpUiEffect.ShowToast(result.data.orEmpty()))
                emitUiEffect(uiEffect = SingUpUiEffect.GoToHomeScreen)
            }
            is Resource.Loading -> {
                updateUiState { copy(isLoading = true) }
            }
            is Resource.Error -> {
                updateUiState { copy(isLoading = false) }
                emitUiEffect(SingUpUiEffect.ShowToast(result.message.orEmpty()))
            }
        }
    }







    private fun updateUiState(block: SingUpUiState.() -> SingUpUiState) {
        _signUpUiState.update(block)
    }

    private suspend fun emitUiEffect(uiEffect: SingUpUiEffect) {
        _signUpUiEffect.send(uiEffect)
    }
}