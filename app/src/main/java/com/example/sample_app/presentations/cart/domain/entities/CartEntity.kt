package com.example.sample_app.presentations.cart.domain.entities

data class CartEntity(
    val name: String,
    val price: Double,
    val image: String,
    var quantity: Int
)