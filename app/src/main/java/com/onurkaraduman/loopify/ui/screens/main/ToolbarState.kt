package com.onurkaraduman.loopify.ui.screens.main

object MainContract {

    data class ToolbarState(
        val title: String = "",
        val showBackButton: Boolean = false
    )

    sealed class MainUiAction(){
        data object FetchToolbar: MainUiAction()
    }

    sealed class MainUiEffect(){
        data class SetTitle(val userName: String): MainUiEffect()
    }
}
