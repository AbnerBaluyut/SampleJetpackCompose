package com.example.sample_app.core.data

import com.example.sample_app.core.data.models.Cart
import com.example.sample_app.core.util.Constant
import com.example.sample_app.core.data.models.Product
import retrofit2.http.GET


interface ApiService {

    @GET(Constant.PRODUCTS)
    suspend fun getProducts() : List<Product>

    @GET(Constant.CATEGORIES)
    suspend fun getCategories(): List<String>

    @GET(Constant.CARTS)
    suspend fun getCarts(): List<Cart>
}