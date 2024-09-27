package com.onurkaraduman.loopify.ui.screens.categories.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.onurkaraduman.loopify.R
import com.onurkaraduman.loopify.domain.model.categories.CategoriesModel

@Composable
fun CategoriesCard(
    modifier: Modifier = Modifier,
    category: CategoriesModel,
    onClick: (String) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClick(category.endPoint)
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .size(height = 65.dp, width = 200.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally // Yatay merkezleme eklendi
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically // Dikey merkezleme
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .align(Alignment.CenterVertically), // Text için dikey ortalama
                    text = category.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    maxLines = 1
                )

                IconButton(modifier = Modifier.size(40.dp)
                    .padding(end = 8.dp),
                    onClick = {}) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_right),
                        contentDescription = ""
                    )
                }
            }
        }
    }
}
