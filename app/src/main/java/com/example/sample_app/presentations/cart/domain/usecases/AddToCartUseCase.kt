package com.example.sample_app.presentations.cart.domain.usecases

import android.util.Log
import arrow.core.Either
import com.example.sample_app.core.NetworkError
import com.example.sample_app.core.data.models.Cart
import com.example.sample_app.core.data.repositories.interfaces.CartRepository
import com.example.sample_app.core.extensions.toNetworkError
import com.example.sample_app.presentations.cart.domain.payload.AddToCartParam
import retrofit2.Call
import javax.inject.Inject

interface AddToCartUseCase {
    suspend fun execute(param: AddToCartParam): Either<NetworkError, Cart>
}

class AddToCartUseCaseImpl @Inject constructor(
    private val repository: CartRepository
): AddToCartUseCase {

    override suspend fun execute(param: AddToCartParam): Either<NetworkError, Cart> = repository.addToCart(param = param)
}