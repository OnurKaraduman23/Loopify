package com.onurkaraduman.loopify.ui.screens.sign_in

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.onurkaraduman.loopify.R
import com.onurkaraduman.loopify.ui.screens.sign_in.SignInContract.SignInUiAction
import com.onurkaraduman.loopify.ui.screens.sign_in.SignInContract.SignInUiEffect
import com.onurkaraduman.loopify.ui.screens.sign_in.SignInContract.SignInUiState
import com.onurkaraduman.loopify.ui.theme.LoopifyTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Composable
fun SignInScreen(
    uiState: SignInUiState,
    uiEffect: Flow<SignInUiEffect>,
    onAction: (SignInUiAction) -> Unit,
    onNavigateHomeScreen: () -> Unit,
    onNavigateSignUpScreen: () -> Unit
) {

    val context = LocalContext.current
    val lifeCycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current

    LaunchedEffect(uiEffect, lifeCycleOwner) {
        lifeCycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            uiEffect.collect { effect ->
                when (effect) {
                    is SignInUiEffect.ShowToast -> {
                        Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                    }

                    is SignInUiEffect.GoToSignUpScreen -> {
                        onNavigateSignUpScreen()
                    }

                    is SignInUiEffect.GoToHomeScreen -> {
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


        Image(modifier = Modifier.size(250.dp), painter = painterResource(id= R.drawable.logo), contentDescription = "logo")


        Spacer(modifier = Modifier.height(12.dp))



        EmailAndPasswordContent(
            email = uiState.email,
            password = uiState.password,
            onEmailChange = { onAction(SignInUiAction.ChangeEmail(it)) },
            onPasswordChange = { onAction(SignInUiAction.ChangePassword(it)) },
            onSigInClick = { onAction(SignInUiAction.SignInClick) },
        )
        Spacer(modifier = Modifier.height(36.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(text = "Don't have an account?", color = colorResource(id = R.color.gray))
            Spacer(modifier = Modifier.width(8.dp))
            Text(modifier = Modifier.clickable {
                onNavigateSignUpScreen()
            }, text = "Sing Up", fontWeight = FontWeight.Bold)
        }
    }


}


@Composable
fun EmailAndPasswordContent(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSigInClick: () -> Unit
) {


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

    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 64.dp),
        horizontalArrangement = Arrangement.End
    ) {
        Text(text = "Forgot Password?", color = colorResource(id = R.color.gray))
    }

    Spacer(modifier = Modifier.height(32.dp))


    Button(onClick = onSigInClick) {
        Text(text = "Sign In")
    }


}

@Preview(showBackground = true)
@Composable
fun PreviewSignInScreen() {
    LoopifyTheme {
        SignInScreen(
            uiState = SignInUiState(),
            uiEffect = flow { },
            onAction = {},
            onNavigateHomeScreen = {},
            onNavigateSignUpScreen = {}
        )
    }
}




