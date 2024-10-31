package com.example.sample_app.presentations.products.presentation.products._components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.example.sample_app.R
import com.example.sample_app.core.extensions.toNumberFormat
import com.example.sample_app.core.data.models.Product

@Composable
fun ItemProduct(
    modifier: Modifier = Modifier,
    product: Product,
    onClick: () -> Unit
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
        onClick = onClick,
        content = {
            Column(
                modifier =  Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    BuildImage(
                        image = product.image
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
fun BuildImage(image: String) {

    var isLoading by remember { mutableStateOf(true) }
    var isError by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .height(150.dp),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            alignment = Alignment.Center,
            onState = { state ->
                when (state) {
                    is AsyncImagePainter.State.Loading -> {
                        isLoading = true
                        isError = false
                    }
                    is AsyncImagePainter.State.Success -> {
                        isLoading = false
                        isError = false
                    }
                    is AsyncImagePainter.State.Error -> {
                        isLoading = false
                        isError = true
                    }
                    else -> { }
                }
            },
            contentScale = ContentScale.FillBounds,
        )
        if (isLoading) {
            CircularProgressIndicator()
        }
        if (isError) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground), // Replace with your error drawable
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.Red),
                modifier = Modifier.matchParentSize()
            )
        }
    }
}

@Composable
fun BuildPrice(
    price: Double
) {
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
                text = price.toNumberFormat(),
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