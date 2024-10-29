package com.example.sample_app.presentations.cart.presentation

data class CartViewState(
    var quantity: Int = 0,
    var items: List<String> = listOf("Test 1", "Test 2", "Test 3", "Test 4", "Test 5")
)