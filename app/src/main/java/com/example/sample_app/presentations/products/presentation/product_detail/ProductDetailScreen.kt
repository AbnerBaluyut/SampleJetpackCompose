package com.example.sample_app.presentations.products.presentation.product_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.sample_app.core.util.isPortrait
import com.example.sample_app.presentations.products.domain.model.Product
import com.example.sample_app.presentations.products.presentation.product_detail._components.ProductDetailAppBar

@Composable
internal fun ProductDetailScreen(navController: NavController, product: Product?) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            ProductDetailAppBar(
                navController = navController
            )
        },
        content = { values ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = values.calculateTopPadding()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                content = {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .verticalScroll(rememberScrollState())
                            .padding(horizontal = 10.dp)
                            .padding(all = 8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top,
                        content = {
                            BuildImage(
                                image = product?.image
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            BuildTitle(
                                title = product?.title ?: ""
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            BuildDescription(
                                desc = product?.description ?: ""
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            BuildRating(
                                rating = (product?.rating?.rate ?: 0.0).toFloat()
                            )
                        }
                    )
                    Button(
                        onClick = {

                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 14.dp),
                        content = {
                            Text(
                                text = "Add to cart"
                            )
                        }
                    )
                    Spacer(
                        modifier = Modifier.padding(bottom = values.calculateBottomPadding())
                    )
                }
            )
        }
    )
}

@Composable
fun BuildImage(image: String?) {

    BoxWithConstraints(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center,
        content = {
            val getSize = if (isPortrait()) maxWidth * 0.7f else maxWidth * 0.4f
            AsyncImage(
                model = image,
                contentDescription = image,
                alignment = Alignment.Center,
                modifier = Modifier
                    .size(getSize),
                contentScale = ContentScale.Fit
            )
        }
    )
}

@Composable
fun BuildTitle(title: String) {
    Row(
        content = {
            Text(
                text = "Title:",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                text = title,
                textAlign = TextAlign.Justify,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = Color.Black
            )
        }
    )
}

@Composable
fun BuildDescription(desc: String) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Top,
        content = {
            Text(
                text = "Description:",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                text = desc,
                textAlign = TextAlign.Justify,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = Color.Black
            )
        }
    )
}

@Composable
fun BuildRating(rating: Float) {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        content = {
            Text(
                text = "Rating: ",
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
            )
            for (star in 1..5) {
                val filledStar = star <= rating
                Text(
                    text = if (filledStar) "★" else "☆",
                    fontSize = 16.sp,
                    color = if (filledStar) Color.Yellow else Color.Black,
                    modifier = Modifier.padding(2.dp)
                )
            }
        }
    )
}