package com.example.sample_app.presentations.products.data.repository

import arrow.core.Either
import com.example.sample_app.core.NetworkError
import com.example.sample_app.presentations.products.data.mapper.toNetworkError
import com.example.sample_app.presentations.products.data.remote.ProductsRemoteSource
import com.example.sample_app.presentations.products.domain.model.Product
import com.example.sample_app.presentations.products.domain.repository.ProductsRepository
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val remoteSource: ProductsRemoteSource
): ProductsRepository {

    override suspend fun getProducts(): Either<NetworkError, List<Product>> {
        return Either.catch {
            remoteSource.getProducts()
        }.mapLeft { it.toNetworkError() }
    }

    override suspend fun getCategories(): Either<NetworkError, List<String>> {
        return Either.catch {
            remoteSource.getCategories()
        }.mapLeft { it.toNetworkError() }
    }
}
