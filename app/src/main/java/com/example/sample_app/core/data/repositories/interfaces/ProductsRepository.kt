package com.example.sample_app.core.data.repositories.interfaces

import arrow.core.Either
import com.example.sample_app.core.NetworkError
import com.example.sample_app.core.data.models.Product

interface ProductsRepository {

    suspend fun getProducts() : Either<NetworkError, List<Product>>
    suspend fun getCategories(): Either<NetworkError, List<String>>
}