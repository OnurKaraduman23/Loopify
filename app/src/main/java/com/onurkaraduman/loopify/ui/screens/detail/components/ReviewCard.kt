package com.onurkaraduman.loopify.ui.screens.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.onurkaraduman.loopify.R
import com.onurkaraduman.loopify.data.remote.dto.detail.ReviewDetail
import com.onurkaraduman.loopify.ui.components.RatingBar
import com.onurkaraduman.loopify.ui.theme.LoopifyTheme
@Composable
fun ReviewCard(
    review: ReviewDetail
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp), // Padding boyutunu küçülttük
        elevation = 2.dp, // Kartın yüksekliğini azalttık
        shape = RoundedCornerShape(6.dp), // Köşeleri daha küçük yaptık
        backgroundColor = colorResource(id = R.color.lightGray)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp) // İçerideki padding'i de küçülttük
        ) {

            Text(
                text = review.reviewerName,
                style = MaterialTheme.typography.subtitle1, // Daha küçük bir yazı boyutu
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 2.dp) // Alt padding'i azalttık
            )

            Text(
                text = review.date,
                style = MaterialTheme.typography.caption, // Daha küçük yazı boyutu
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                RatingBar(
                    rating = review.rating.toDouble(),
                    modifier = Modifier.padding(bottom = 4.dp) // Aradaki boşluğu azalttık
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = review.rating.toString(), style = MaterialTheme.typography.caption, fontSize = 12.sp) // Küçük yazı boyutu
            }
            Spacer(modifier = Modifier.height(6.dp)) // Yüksekliği azalttık
            Text(
                text = review.comment,
                style = MaterialTheme.typography.body2 // Yorum için daha küçük yazı boyutu
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewReviewCard() {
    LoopifyTheme {
        ReviewCard(
            ReviewDetail(
                comment = "MyComment",
                date = "27/09/2024",
                rating = 5,
                reviewerEmail = "JohnDoe@example.com",
                reviewerName = "John Doe"
            )
        )
    }
}