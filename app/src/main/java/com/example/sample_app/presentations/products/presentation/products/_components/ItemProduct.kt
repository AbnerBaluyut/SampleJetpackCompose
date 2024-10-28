package com.example.sample_app.presentations.products.presentation.products._components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import java.util.Locale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.sample_app.presentations.products.domain.model.Product
import java.text.NumberFormat

@Composable
fun ItemProduct(
    modifier: Modifier = Modifier,
    product: Product
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        content = {
            Column(
                modifier =  Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    AsyncImage(
                        model = product.image,
                        contentDescription = product.image,
                        modifier = Modifier
                            .fillMaxSize()
                            .width(180.dp)
                            .height(150.dp)
                            .padding(
                                horizontal = 30.dp
                            ),
                        alignment = Alignment.Center,
                        contentScale = ContentScale.FillBounds
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = product.title,
                        fontSize = 14.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Normal,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    BuildRating(
                        rating = product.rating.rate.toFloat()
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    BuildPrice(
                        price = product.price
                    )
                }
            )
        }
    )
}

@Composable
fun BuildPrice(
    price: Double
) {
    val formattedPrice = NumberFormat.getCurrencyInstance(Locale.getDefault()).format(price)
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        content = {
            Text(
                text = "Price: ",
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                text = formattedPrice,
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.Normal,
            )
        }
    )
}

@Composable
fun BuildRating(rating: Float) {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        content = {
            Text(
                text = "Rating: ",
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
            )
            for (star in 1..5) {
                val filledStar = star <= rating
                Text(
                    text = if (filledStar) "★" else "☆",
                    fontSize = 14.sp,
                    color = if (filledStar) Color.Yellow else Color.Black,
                    modifier = Modifier.padding(2.dp)
                )
            }
        }
    )
}