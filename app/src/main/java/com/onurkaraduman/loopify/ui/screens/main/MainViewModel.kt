package com.onurkaraduman.loopify.ui.screens.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    private val _toolbarState = MutableStateFlow(ToolbarState())
    val toolbarState: StateFlow<ToolbarState> = _toolbarState.asStateFlow()

    fun updateToolbarState(newState: ToolbarState) {
        _toolbarState.value = newState
    }
}
