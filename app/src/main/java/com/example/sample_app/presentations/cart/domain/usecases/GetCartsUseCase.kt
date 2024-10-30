package com.example.sample_app.presentations.cart.domain.usecases

import arrow.core.Either
import com.example.sample_app.core.NetworkError
import com.example.sample_app.core.data.models.Cart
import com.example.sample_app.core.data.repositories.interfaces.CartRepository
import javax.inject.Inject

interface GetCartsUseCase {
    suspend fun execute(): Either<NetworkError, List<Cart>>
}

class GetCartsUseCaseImpl @Inject constructor(
    private val repository: CartRepository
): GetCartsUseCase {

    override suspend fun execute(): Either<NetworkError, List<Cart>> {
        return repository.getCarts()
    }
}