package com.example.sample_app.core.data.models

import com.google.gson.annotations.SerializedName

data class Cart(
    val id: Int,
    val userId: Int,
    val date: String,
    val products: List<CartProduct>
) {
    data class CartProduct(
        @SerializedName("productId") val id: Int,
        val quantity: Int,
    )
}