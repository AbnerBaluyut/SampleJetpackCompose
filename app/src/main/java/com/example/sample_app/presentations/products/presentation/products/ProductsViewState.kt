package com.example.sample_app.presentations.products.presentation.products

import com.example.sample_app.core.data.models.Product

data class ProductsViewState(
    val isLoading: Boolean = false,
    var isPullToRefresh: Boolean = false,
    var tabIndex: Int = 0,
    val products: List<Product> = emptyList(),
    val categories: List<String> = emptyList(),
    val error: String? = null
)
