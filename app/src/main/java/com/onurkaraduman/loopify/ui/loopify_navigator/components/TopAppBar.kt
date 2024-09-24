package com.onurkaraduman.loopify.ui.loopify_navigator.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.onurkaraduman.loopify.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(showBackButton: Boolean = false) {
    Column {
        TopAppBar(
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Hello John", fontWeight = FontWeight.Bold)
                }
            },
            navigationIcon = {
                if (showBackButton) {
                    IconButton(onClick = {
                        // TODO: Geri gitme işlemi
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "menu items"
                        )
                    }
                }
            },
            actions = {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = "more options",
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = colorResource(id = R.color.lightGray)),
        )
        // HorizontalDivider ekle
        HorizontalDivider(thickness = 2.dp, color = Color.Black) // Çizgi ekleniyor
    }
}
