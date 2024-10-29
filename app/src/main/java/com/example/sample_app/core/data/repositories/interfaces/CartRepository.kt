package com.example.sample_app.core.data.repositories.interfaces

import arrow.core.Either
import com.example.sample_app.core.NetworkError
import com.example.sample_app.core.data.models.Cart

interface CartRepository {

    suspend fun getCarts(): Either<NetworkError, List<Cart>>
}