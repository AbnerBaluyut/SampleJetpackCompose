package com.example.sample_app.presentations.products.presentation.product_detail

import androidx.compose.foundation.Image
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.example.sample_app.R
import com.example.sample_app.core.util.isPortrait
import com.example.sample_app.core.data.models.Product
import com.example.sample_app.presentations.products.presentation.product_detail._components.ProductDetailAppBar

@Composable
internal fun ProductDetailScreen(
    navController: NavController,
    product: Product?,
    viewModel: ProductDetailViewModel = hiltViewModel()
) {


    val state by viewModel.state.collectAsStateWithLifecycle()

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
                            viewModel.addToCart(
                                product = product
                            )
                        },
                        enabled = !state.isLoading,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 14.dp),
                        content = {
                            if (state.isLoading) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(20.dp),
                                )
                            } else {
                                Text(
                                    text = "Add to cart"
                                )
                            }
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
    var isLoading by remember { mutableStateOf(true) }
    var isError by remember { mutableStateOf(false) }

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
                contentScale = ContentScale.Fit,
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
                }
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