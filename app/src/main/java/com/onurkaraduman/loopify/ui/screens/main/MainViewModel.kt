package com.onurkaraduman.loopify.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.onurkaraduman.loopify.common.Resource
import com.onurkaraduman.loopify.data.repository.auth_repository.AuthRepository
import com.onurkaraduman.loopify.ui.screens.main.MainContract.MainUiAction
import com.onurkaraduman.loopify.ui.screens.main.MainContract.MainUiEffect
import com.onurkaraduman.loopify.ui.screens.main.MainContract.ToolbarState
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
class MainViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _toolbarState = MutableStateFlow(ToolbarState())
    val toolbarState: StateFlow<ToolbarState> = _toolbarState.asStateFlow()

    private val _toolbarUiEffect by lazy { Channel<MainUiEffect>() }
    val toolbarUiEffect: Flow<MainUiEffect> by lazy { _toolbarUiEffect.receiveAsFlow() }

    fun updateToolbarState(newState: ToolbarState) {
        _toolbarState.value = newState
    }

    fun onAction(uiAction: MainUiAction){
        when(uiAction) {
            is MainUiAction.FetchToolbar -> fetchUserName()
        }
    }


    fun fetchUserName() {
        viewModelScope.launch {
            val userId = FirebaseAuth.getInstance().currentUser?.uid
            if (userId != null) {
                val result = authRepository.getUserName(userId)
                if (result is Resource.Success) {
                    emitUiEffect(MainUiEffect.SetTitle("Hi, ${result.data.orEmpty()}"))
                } else {
                    emitUiEffect(MainUiEffect.SetTitle("Hi, Guest"))
                }
            }
        }
    }


    private fun updateUiState(block: ToolbarState.() -> ToolbarState) {
        _toolbarState.update(block)
    }

    private suspend fun emitUiEffect(uiEffect: MainUiEffect) {
        _toolbarUiEffect.send(uiEffect)
    }
}
