package com.onurkaraduman.loopify.ui.screens.splash

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.onurkaraduman.loopify.R
import com.onurkaraduman.loopify.ui.theme.LoopifyTheme
import com.onurkaraduman.loopify.ui.screens.splash.SplashContract.SplashUiEffect
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

@Composable
fun SplashScreen(
    uiEffect: Flow<SplashUiEffect>,
    onNavigateHomeScreen: () -> Unit,
    onNavigateSingInScreen: () -> Unit
) {

    val context = LocalContext.current
    val lifeCycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current

    LaunchedEffect(uiEffect, lifeCycleOwner) {
        delay(5000)
        lifeCycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            uiEffect.collect { effect ->
                when (effect) {
                    is SplashUiEffect.ShowToast -> {
                        Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                    }

                    is SplashUiEffect.GoToMainScreen -> {
                        onNavigateHomeScreen()
                    }
                    is SplashUiEffect.GoToSignInScreen -> {
                        onNavigateSingInScreen()
                    }
                }

            }
        }
    }

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_anim_splash))
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(modifier = Modifier.size(350.dp), composition = composition)

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSplashScreen() {
    LoopifyTheme {
        SplashScreen(
            uiEffect = flow {  },
            onNavigateHomeScreen = {},
            onNavigateSingInScreen = {}
        )
    }
}