package com.onurkaraduman.loopify.ui.loopify_navigator.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.onurkaraduman.loopify.R
import com.onurkaraduman.loopify.ui.theme.LoopifyTheme

@Composable
fun LoopifyBottomNavigation(
    items: List<BottomNavigationItem>,
    selectedItem: Int,
    onItemClick: (Int) -> Unit
) {
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth(),
        containerColor = colorResource(id = R.color.lightGray),
        tonalElevation = 10.dp
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selectedItem,
                onClick = { onItemClick(index) },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = null,
                        modifier = Modifier
                            .size(26.dp)
                            .align(CenterVertically)
                    )
                },
                label = {
                    Text(
                        text = item.text,
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 14.sp), // Yazı boyutunu artır
                        modifier = Modifier.align(CenterVertically) // Yazıyı dikey olarak ortala
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = colorResource(id = R.color.black),
                    selectedTextColor = colorResource(id = R.color.black),
                    unselectedIconColor = colorResource(id = R.color.gray),
                    unselectedTextColor = colorResource(id = R.color.gray),
                    indicatorColor = colorResource(id = R.color.white)
                ),
            )
        }
    }
}


data class BottomNavigationItem(
    @DrawableRes val icon: Int,
    val text: String
)

@Preview(showBackground = true)
@Composable
fun NewsBottomNavigationPreview() {
    LoopifyTheme {
        LoopifyBottomNavigation(items = listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_category, text = "Profile"),
            BottomNavigationItem(icon = R.drawable.ic_favorite_fill, text = "Favorites"),
        ), selectedItem = 0, onItemClick = {})
    }

}