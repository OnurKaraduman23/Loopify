import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.onurkaraduman.loopify.domain.model.products.ProductsModel
import com.onurkaraduman.loopify.ui.theme.LoopifyTheme

@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    productList: ProductsModel,
    onClick: () -> Unit
) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .size(width = 200.dp, height = 240.dp) // Kartın boyutu
            .padding(8.dp), // Dış boşluklar
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp), // Kabartma efekti
        shape = RoundedCornerShape(16.dp), // Köşeleri yuvarlama
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp), // Resim yüksekliği
                model = ImageRequest.Builder(context).data(productList.image).build(),
                contentDescription = "Product thumbnail Image",
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .fillMaxWidth(),
                text = productList.title,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp, // Daha uyumlu yazı boyutu
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = productList.category,
                    color = Color.Gray,
                    fontSize = 12.sp,
                    maxLines = 1
                )

                Text(
                    text = "$ ${productList.price}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProductCard() {
    LoopifyTheme {
        ProductCard(
            productList = ProductsModel(
                id = 1,
                title = "Huawei Watch GT4 Pro Huawei Watch GT4 Pro Huawei Watch",
                price = 12999,
                category = "Accessories",
                tags = listOf("Accessories", "Watch"),
                image = "https://www.apple.com/v/watch/bo/images/overview/select/product_se__frx4hb13romm_xlarge.png",
                rating = 4
            ),
            onClick = {}
        )
    }
}
