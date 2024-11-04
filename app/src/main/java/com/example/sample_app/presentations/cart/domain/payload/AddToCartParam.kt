package com.example.sample_app.presentations.cart.domain.payload

import com.example.sample_app.core.data.models.Cart

data class AddToCartParam(
    val userId: Int,
    val date: String,
    val products: List<Cart.CartProduct>
)