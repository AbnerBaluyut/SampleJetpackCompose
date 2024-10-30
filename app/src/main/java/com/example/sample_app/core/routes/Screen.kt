package com.example.sample_app.core.routes

sealed class Screen(val route: String) {
    data object Main: Screen("main_screen")
    data object ProductDetail: Screen("product_detail?id={id}&json={json}") {
        fun createRoute(id: Int, json: String) : String {
            return "product_detail?id=$id&json=$json"
        }
    }
    data object Cart: Screen("cart?json={json}") {
        fun createRoute(json: String): String {
            return "cart?json=$json"
        }
    }
}