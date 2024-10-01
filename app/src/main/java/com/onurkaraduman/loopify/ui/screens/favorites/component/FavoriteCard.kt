package com.onurkaraduman.loopify.ui.screens.favorites.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.onurkaraduman.loopify.R
import com.onurkaraduman.loopify.data.local.entity.ProductEntity
import com.onurkaraduman.loopify.ui.theme.LoopifyTheme

@Composable
fun FavoriteCard(
    modifier: Modifier = Modifier,
    favoriteProduct: ProductEntity,
    onClick: (Int) -> Unit,
    onClickDelete: (ProductEntity) -> Unit
) {
    val context = LocalContext.current
    Row(modifier = modifier.clickable{ onClick(favoriteProduct.id) }.fillMaxWidth()) {
        AsyncImage(
            modifier = Modifier
                .size(96.dp)
                .clip(MaterialTheme.shapes.medium),
            model = ImageRequest.Builder(context).data(favoriteProduct.image).build(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
            Column(
                verticalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .height(96.dp)
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = favoriteProduct.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    maxLines = 2
                )
                Row(modifier = modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = "$ ${favoriteProduct.price}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    IconButton(onClick = {onClickDelete(favoriteProduct)}){
                        Icon(painter = painterResource(R.drawable.ic_delete), contentDescription = "")
                    }
                }
            }
        }



}

@Preview(showBackground = true)
@Composable
fun PreviewFavoriteCard() {
    LoopifyTheme {
        FavoriteCard(favoriteProduct = ProductEntity(
            id = 1,
            title = "My Product Title Lorem Ipsum",
            price = 15,
            category = "Accessories",
            image = ""
        ), onClick = {}, onClickDelete = {})
    }
}