package com.example.sample_app.core.data.repositories.impl

import arrow.core.Either
import com.example.sample_app.core.NetworkError
import com.example.sample_app.core.data.ApiService
import com.example.sample_app.core.data.models.Cart
import com.example.sample_app.core.data.repositories.interfaces.CartRepository
import com.example.sample_app.core.extensions.toNetworkError
import com.example.sample_app.presentations.cart.domain.payload.AddToCartParam
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): CartRepository {

    override suspend fun getCarts(): Either<NetworkError, List<Cart>> {
        return Either.catch {
            apiService.getCarts()
        }.mapLeft { error -> error.toNetworkError() }
    }

    override suspend fun addToCart(param: AddToCartParam): Either<NetworkError, Cart> {
        return Either.catch {
            apiService.addToCart(param =  param)
        }.mapLeft { error -> error.toNetworkError() }
    }
}