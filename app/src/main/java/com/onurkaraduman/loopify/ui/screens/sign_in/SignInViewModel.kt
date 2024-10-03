package com.onurkaraduman.loopify.ui.screens.sign_in


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onurkaraduman.loopify.common.Resource
import com.onurkaraduman.loopify.data.repository.auth_repository.AuthRepository
import com.onurkaraduman.loopify.ui.screens.sign_in.SignInContract.SignInUiAction
import com.onurkaraduman.loopify.ui.screens.sign_in.SignInContract.SignInUiEffect
import com.onurkaraduman.loopify.ui.screens.sign_in.SignInContract.SignInUiState
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
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {

    private val _signInUiState = MutableStateFlow(SignInUiState())
    val signInUiState: StateFlow<SignInUiState> = _signInUiState.asStateFlow()


    private val _signInUiEffect by lazy { Channel<SignInUiEffect>() }
    val signInUiEffect: Flow<SignInUiEffect> by lazy { _signInUiEffect.receiveAsFlow() }

    fun onAction(uiAction: SignInUiAction){
        when(uiAction){
            is SignInUiAction.SignInClick -> signIn()
            is SignInUiAction.ChangeEmail -> updateUiState { copy(email = uiAction.email) }
            is SignInUiAction.ChangePassword -> updateUiState { copy(password = uiAction.password) }
        }
    }

    private fun signIn() = viewModelScope.launch {

        when(val result = authRepository.signIn(signInUiState.value.email,signInUiState.value.password)){
            is Resource.Success -> {
                emitUiEffect(SignInUiEffect.ShowToast(result.data.orEmpty()))
                emitUiEffect(SignInUiEffect.GoToHomeScreen)
            }
            is Resource.Loading -> {}
            is Resource.Error -> {
                emitUiEffect(SignInUiEffect.ShowToast(result.message.orEmpty()))
            }
        }


    }

    private fun updateUiState(block: SignInUiState.() -> SignInUiState) {
        _signInUiState.update(block)
    }

    private suspend fun emitUiEffect(uiEffect: SignInUiEffect) {
        _signInUiEffect.send(uiEffect)
    }


}