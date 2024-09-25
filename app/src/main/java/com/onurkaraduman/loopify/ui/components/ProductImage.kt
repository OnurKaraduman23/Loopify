package com.onurkaraduman.loopify.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun ProductImage(
    imageUrl: String,
    height: Int,
    contentScale: ContentScale
) {
    val context = LocalContext.current
    val state = remember { mutableStateOf<ImageLoadState>(ImageLoadState.Loading) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = height.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = height.dp),
            model = ImageRequest.Builder(context)
                .data(imageUrl)
                .listener(
                    onSuccess = { _, _ -> state.value = ImageLoadState.Success },
                    onError = { _, _ -> state.value = ImageLoadState.Error }
                )
                .build(),
            contentDescription = "Product thumbnail Image",
            contentScale = contentScale
        )

        when (state.value) {
            is ImageLoadState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height = height.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is ImageLoadState.Success -> {
                // Görsel yüklendiği için burada herhangi bir şey yapmaya gerek yok
            }
            is ImageLoadState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height = height.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Error loading image", color = Color.Red)
                }
            }
        }
    }
}

sealed class ImageLoadState {
    data object Loading : ImageLoadState()
    data object Success : ImageLoadState()
    data object Error : ImageLoadState()
}
