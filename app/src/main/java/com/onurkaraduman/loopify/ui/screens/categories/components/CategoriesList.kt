package com.onurkaraduman.loopify.ui.screens.categories.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.onurkaraduman.loopify.domain.model.categories.CategoriesModel

@Composable
fun CategoriesList(
    categories: List<CategoriesModel>,
    onClick: (String) -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(categories) { category ->
            CategoriesCard(category = category, onClick = { endPoint ->
                onClick(endPoint)
            })
        }
    }
}