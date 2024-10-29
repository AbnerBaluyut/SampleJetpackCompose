package com.example.sample_app.presentations.products.domain

import arrow.core.Either
import com.example.sample_app.core.NetworkError
import com.example.sample_app.core.data.repositories.interfaces.ProductsRepository
import javax.inject.Inject

interface GetCategoriesUseCase {
    suspend fun execute(): Either<NetworkError, List<String>>
}

class GetCategoriesUseCaseImpl @Inject constructor(
    private val repository: ProductsRepository
): GetCategoriesUseCase {

    override suspend fun execute(): Either<NetworkError, List<String>> {
        return repository.getCategories()
    }
}