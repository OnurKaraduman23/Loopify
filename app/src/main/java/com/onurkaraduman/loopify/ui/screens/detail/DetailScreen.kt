package com.onurkaraduman.loopify.ui.screens.detail

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.onurkaraduman.loopify.R
import com.onurkaraduman.loopify.domain.model.details.ProductDetailsModel
import com.onurkaraduman.loopify.ui.components.RatingBar
import com.onurkaraduman.loopify.ui.screens.common.ErrorScreen
import com.onurkaraduman.loopify.ui.screens.common.LoadingScreen
import com.onurkaraduman.loopify.ui.screens.detail.DetailContract.DetailUiAction
import com.onurkaraduman.loopify.ui.screens.detail.DetailContract.DetailUiEffect
import com.onurkaraduman.loopify.ui.screens.detail.DetailContract.DetailUiState
import com.onurkaraduman.loopify.ui.screens.detail.components.ProductImageSlider
import com.onurkaraduman.loopify.ui.screens.detail.components.ReviewCard
import com.onurkaraduman.loopify.ui.theme.LoopifyTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Composable
fun DetailScreen(
    detailUiState: DetailUiState,
    detailUiEffect: Flow<DetailUiEffect>,
    onAction: (DetailUiAction) -> Unit,
    onNavigateCardScreen: () -> Unit,
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(detailUiEffect, lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            detailUiEffect.collect { effect ->
                when (effect) {
                    is DetailUiEffect.ShowToastMessage -> {
                        Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                    }

                    is DetailUiEffect.GoToCardScreen -> {
                        onNavigateCardScreen()
                    }
                }
            }
        }
    }

    when {
        detailUiState.isLoading -> {
            LoadingScreen()
        }

        detailUiState.productDetails != null -> {
            ProductDetailContent(
                productDetails = detailUiState.productDetails,
                onAction = onAction
            )
        }

        else -> {
            ErrorScreen(message = detailUiState.errorMessage.orEmpty())
        }
    }
}

@Composable
fun ProductDetailContent(
    productDetails: ProductDetailsModel,
    onAction: (DetailUiAction) -> Unit
) {
    Scaffold(
        bottomBar = {
            AddToCartButton(onAction = onAction)
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                ProductDetailView(productDetails = productDetails, onClick = {
                    onAction(
                        DetailUiAction.AddToFavoriteClick
                    )
                } )
                Spacer(modifier = Modifier.height(22.dp))
            }

            items(productDetails.reviewList) { review ->
                ReviewCard(review)
                Spacer(modifier = Modifier.height(16.dp)) // Her bir incelemeden sonra boşluk
            }
        }
    }
}

@Composable
fun AddToCartButton(onAction: (DetailUiAction) -> Unit) {
    Button(
        onClick = { onAction(DetailUiAction.AddToCardClick) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(text = "Add to Cart", modifier = Modifier.padding(8.dp))
    }
}

@Composable
fun ProductDetailView(
    productDetails: ProductDetailsModel,
    onClick: (DetailUiAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box {
            ProductImageSlider(imageUrls = productDetails.imageList)
            IconButton(
                onClick = { onClick(DetailUiAction.AddToFavoriteClick) },
                modifier = Modifier.align(Alignment.TopEnd).padding(16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_favorite_border),
                    contentDescription = "Favorite Icon"
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = productDetails.title,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "$ ${productDetails.price}",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = 16.dp),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = productDetails.descriptions,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RatingBar(rating = productDetails.rating)
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = productDetails.rating.toString(), fontSize = 16.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailScreen(
    @PreviewParameter(DetailScreenPreviewProvider::class) detailUiState: DetailUiState
) {
    LoopifyTheme {
        DetailScreen(
            detailUiState = detailUiState,
            detailUiEffect = flow {},
            onAction = {},
            onNavigateCardScreen = {}
        )
    }
}

