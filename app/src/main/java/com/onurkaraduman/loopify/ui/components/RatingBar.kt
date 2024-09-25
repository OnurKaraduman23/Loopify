package com.onurkaraduman.loopify.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Double,
    maxRating: Int = 5
) {
    Row(
//        modifier = Modifier.padding(start = 12.dp)
    ) {
        for (i in 1..maxRating) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Star $i",
                modifier = Modifier.size(18.dp),
                tint = if (i <= rating) Color.Yellow else Color.Gray
            )
            if (i < maxRating) {
                Spacer(modifier = Modifier.width(4.dp))
            }
        }
    }
}