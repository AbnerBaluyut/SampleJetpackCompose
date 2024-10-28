package com.example.sample_app.presentations.products.presentation.products

import com.example.sample_app.presentations.products.domain.model.Product

data class ProductsScreenState(
    val isLoading: Boolean = false,
    var tabIndex: Int = 0,
    val products: List<Product> = emptyList(),
    val categories: List<String> = emptyList(),
    val error: String? = null
)
