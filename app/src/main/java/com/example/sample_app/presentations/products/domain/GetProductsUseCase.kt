package com.example.sample_app.presentations.products.domain

import arrow.core.Either
import com.example.sample_app.core.NetworkError
import com.example.sample_app.core.data.models.Product
import com.example.sample_app.core.data.repositories.interfaces.ProductsRepository
import javax.inject.Inject

interface GetProductsUseCase {
    suspend fun execute(): Either<NetworkError, List<Product>>
}

class GetProductsUseCaseImpl @Inject constructor(
    private val repository: ProductsRepository
): GetProductsUseCase {

    override suspend fun execute(): Either<NetworkError, List<Product>> {
        return repository.getProducts()
    }
}