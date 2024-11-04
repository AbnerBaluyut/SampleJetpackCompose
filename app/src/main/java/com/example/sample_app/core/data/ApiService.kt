package com.example.sample_app.core.data

import com.example.sample_app.core.data.models.Cart
import com.example.sample_app.core.util.Constant
import com.example.sample_app.core.data.models.Product
import com.example.sample_app.presentations.cart.domain.payload.AddToCartParam
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {

    @GET(Constant.PRODUCTS)
    suspend fun getProducts() : List<Product>

    @GET(Constant.CATEGORIES)
    suspend fun getCategories(): List<String>

    @GET(Constant.CARTS)
    suspend fun getCarts(): List<Cart>

    @POST(Constant.CARTS)
    suspend fun addToCart(@Body param: AddToCartParam): Cart
}