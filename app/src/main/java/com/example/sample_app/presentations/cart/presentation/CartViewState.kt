package com.example.sample_app.presentations.cart.presentation

import com.example.sample_app.presentations.cart.domain.entities.CartEntity

data class CartViewState(
    val items: List<CartEntity> = emptyList(),
    val error: String? = null,
    val isLoading: Boolean = false
)