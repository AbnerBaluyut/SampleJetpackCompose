package com.example.sample_app.core.data.repositories.impl

import arrow.core.Either
import com.example.sample_app.core.NetworkError
import com.example.sample_app.core.data.ApiService
import com.example.sample_app.core.extensions.toNetworkError
import com.example.sample_app.core.data.models.Product
import com.example.sample_app.core.data.repositories.interfaces.ProductsRepository
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): ProductsRepository {

    override suspend fun getProducts(): Either<NetworkError, List<Product>> {
        return Either.catch {
            apiService.getProducts()
        }.mapLeft { it.toNetworkError() }
    }

    override suspend fun getCategories(): Either<NetworkError, List<String>> {
        return Either.catch {
            apiService.getCategories()
        }.mapLeft { it.toNetworkError() }
    }
}
