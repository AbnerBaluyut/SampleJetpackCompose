package com.example.sample_app.core.extensions

import com.example.sample_app.core.ApiError
import com.example.sample_app.core.NetworkError
import retrofit2.HttpException
import java.io.IOException

fun Throwable.toNetworkError() : NetworkError {
    val error = when (this) {
        is IOException -> ApiError.NetworkError
        is HttpException -> ApiError.UnknownResponse
        else -> ApiError.UnknownError
    }

    return NetworkError(
        error = error,
        t = this
    )
}
