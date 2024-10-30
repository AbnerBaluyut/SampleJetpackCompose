package com.example.sample_app.presentations.cart.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.sample_app.core.data.models.Product
import com.example.sample_app.presentations.cart.presentation._components.CartAppBar
import com.example.sample_app.presentations.cart.presentation._components.CartItem
import com.example.sample_app.presentations.products.presentation.products.ProductsViewModel

@Composable
fun CartScreen(
    navController: NavController,
    products: List<Product>,
    viewModel: CartViewModel = hiltViewModel(),
) {

    LaunchedEffect(products) {
        viewModel.addProducts(products)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CartAppBar(
                title = "My Cart",
                navController = navController,
                onTapDelete = {

                }
            )
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = Color.Gray.copy(alpha = 0.1f)
                    )
                    .padding(top = it.calculateTopPadding())
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxHeight(),
                    contentPadding = PaddingValues(10.dp),
                    content = {
                        items(
                            count = state.items.size,
                            itemContent = { index ->
                                CartItem(
                                    item = state.items[index]
                                )
                            }
                        )
                    }
                )
            }
        }
    )
}