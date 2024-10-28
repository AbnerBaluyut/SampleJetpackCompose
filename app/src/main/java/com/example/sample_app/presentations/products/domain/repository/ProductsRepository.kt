package com.example.sample_app.presentations.products.domain.repository

import arrow.core.Either
import com.example.sample_app.core.NetworkError
import com.example.sample_app.presentations.products.domain.model.Product

interface ProductsRepository {

    suspend fun getProducts() : Either<NetworkError, List<Product>>
    suspend fun getCategories(): Either<NetworkError, List<String>>
}
