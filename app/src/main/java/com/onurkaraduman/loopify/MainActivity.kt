package com.onurkaraduman.loopify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.onurkaraduman.loopify.ui.navigation.LoopifyNavGraph
import com.onurkaraduman.loopify.ui.navigation.Route
import com.onurkaraduman.loopify.ui.theme.LoopifyTheme
import dagger.hilt.android.AndroidEntryPoint


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoopifyTheme {
                LoopifyNavGraph(Route.HomeScreen.route)
                }
            }
        }
    }

