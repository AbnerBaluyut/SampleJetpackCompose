package com.example.sample_app.core.routes

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.sample_app.core.ArgKey
import com.example.sample_app.core.extensions.fromJson
import com.example.sample_app.presentations.cart.presentation.CartScreen
import com.example.sample_app.core.data.models.Product
import com.example.sample_app.core.extensions.fromJsonToList
import com.example.sample_app.presentations.products.presentation.product_detail.ProductDetailScreen
import com.example.sample_app.presentations.products.presentation.products.ProductsScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Main.route) {
        composable(route = Screen.Main.route) {
            ProductsScreen(navController = navController)
        }
        composable(
            route = Screen.ProductDetail.route,
            arguments = listOf(
                navArgument(ArgKey.ID) {
                    type = NavType.IntType
                },
                navArgument(ArgKey.JSON) {
                    type = NavType.StringType
                }
            )
        ) {
            val productJson = it.arguments?.getString(ArgKey.JSON)
            val product = productJson?.fromJson<Product>()
            ProductDetailScreen(navController = navController, product = product)
        }
        composable(
            route = Screen.Cart.route,
            arguments = listOf(
                navArgument(ArgKey.JSON) {
                    type = NavType.StringType
                }
            )
        ) {
            val productsJson = it.arguments?.getString(ArgKey.JSON)
            val products = productsJson?.fromJsonToList<Product>()
            CartScreen(navController = navController, products = products ?: emptyList())
        }
    }
}
