package com.onurkaraduman.loopify.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.onurkaraduman.loopify.common.Resource
import com.onurkaraduman.loopify.data.repository.auth_repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _toolbarState = MutableStateFlow(ToolbarState())
    val toolbarState: StateFlow<ToolbarState> = _toolbarState.asStateFlow()

    fun updateToolbarState(newState: ToolbarState) {
        _toolbarState.value = newState
    }


    fun fetchUserName() {
        viewModelScope.launch {
            val userId = FirebaseAuth.getInstance().currentUser?.uid
            if (userId != null) {
                val result = authRepository.getUserName(userId)
                if (result is Resource.Success) {
                    updateUiState { copy(title = "Hi, ${result.data.orEmpty()}") }
                } else {
                    updateUiState { copy(title = "Guest") }
                }
            }
        }
    }


    private fun updateUiState(block: ToolbarState.() -> ToolbarState) {
        _toolbarState.update(block)
    }

}
