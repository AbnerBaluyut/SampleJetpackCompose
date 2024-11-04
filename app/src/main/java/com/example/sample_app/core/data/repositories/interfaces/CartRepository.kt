package com.example.sample_app.core.data.repositories.interfaces

import arrow.core.Either
import com.example.sample_app.core.NetworkError
import com.example.sample_app.core.data.models.Cart
import com.example.sample_app.presentations.cart.domain.payload.AddToCartParam
import retrofit2.Call

interface CartRepository {

    suspend fun getCarts(): Either<NetworkError, List<Cart>>
    suspend fun addToCart(param: AddToCartParam): Either<NetworkError, Cart>
}