package com.onurkaraduman.loopify.ui.screens.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.onurkaraduman.loopify.ui.theme.LoopifyTheme

@Composable
fun ErrorScreen(message: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(
            modifier = Modifier.size(150.dp),
            imageVector = Icons.Default.Warning,
            contentDescription = "Warning"
        )
        Text(
            modifier = Modifier.padding(horizontal = 12.dp),
            text = message,
            color = Color.Black,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = { onClick.invoke() }) {
            Text("Retry")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewErrorScreen() {
    LoopifyTheme {
        ErrorScreen(message = "Couldn't reach Server. Check Your Internet Connection", onClick = {})
    }
}