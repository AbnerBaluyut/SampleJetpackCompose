package com.example.sample_app.presentations.products.data.remote

import com.example.sample_app.core.util.Constant
import com.example.sample_app.presentations.products.domain.model.Product
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductsRemoteSource {

    @GET(Constant.PRODUCTS)
    suspend fun getProducts() : List<Product>

    @GET(Constant.CATEGORIES)
    suspend fun getCategories(): List<String>
}
