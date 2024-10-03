package com.onurkaraduman.loopify.ui.screens.sign_up

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.onurkaraduman.loopify.ui.screens.sign_up.SignUpContract.SingUpUiAction
import com.onurkaraduman.loopify.ui.screens.sign_up.SignUpContract.SingUpUiEffect
import com.onurkaraduman.loopify.ui.screens.sign_up.SignUpContract.SingUpUiState
import com.onurkaraduman.loopify.ui.theme.LoopifyTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Composable
fun SignUpScreen(
    uiState: SingUpUiState,
    uiEffect: Flow<SingUpUiEffect>,
    onAction: (SingUpUiAction) -> Unit,
    onNavigateHomeScreen: () -> Unit
) {

    val context = LocalContext.current
    val lifeCycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current

    LaunchedEffect(uiEffect, lifeCycleOwner) {
        lifeCycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            uiEffect.collect { effect ->
                when (effect) {
                    is SingUpUiEffect.ShowToast -> {
                        Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                    }

                    is SingUpUiEffect.GoToHomeScreen -> {
                        onNavigateHomeScreen()
                    }

                }

            }
        }
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        EmailAndPasswordContent(
            email = uiState.email,
            password = uiState.password,
            onEmailChange = { onAction(SingUpUiAction.ChangeEmail(it)) },
            onPasswordChange = { onAction(SingUpUiAction.ChangePassword(it)) },
            onSignUpClick = { onAction(SingUpUiAction.SignUpClick) },
        )
    }


}

@Composable
fun EmailAndPasswordContent(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignUpClick: () -> Unit
) {
    Text(text = "Sign Up", fontWeight = FontWeight.Bold, fontSize = 24.sp)
    Spacer(modifier = Modifier.height(32.dp))

    OutlinedTextField(
        value = email,
        maxLines = 1,
        placeholder = { Text(text = "Email") },
        onValueChange = onEmailChange
    )
    Spacer(modifier = Modifier.height(16.dp))

    OutlinedTextField(
        value = password,
        maxLines = 1,
        placeholder = { Text(text = "Password") },
        onValueChange = onPasswordChange
    )

    Spacer(modifier = Modifier.height(16.dp))


    Spacer(modifier = Modifier.height(32.dp))


    Button(onClick = onSignUpClick) {
        Text(text = "Sign Up")
    }


}


@Preview(showBackground = true)
@Composable
fun PreviewSignUpScreen() {
    LoopifyTheme {
        SignUpScreen(
            uiState = SingUpUiState(),
            uiEffect = flow { },
            onAction = {},
            onNavigateHomeScreen = {}
        )
    }
}