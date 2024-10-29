package com.example.sample_app.di

import com.example.sample_app.core.data.ApiService
import com.example.sample_app.core.data.repositories.impl.CartRepositoryImpl
import com.example.sample_app.core.data.repositories.impl.ProductsRepositoryImpl
import com.example.sample_app.core.data.repositories.interfaces.CartRepository
import com.example.sample_app.core.data.repositories.interfaces.ProductsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideProductsRepository(apiService: ApiService) : ProductsRepository {
        return ProductsRepositoryImpl(
            apiService = apiService
        )
    }

    @Provides
    @Singleton
    fun provideCartRepository(apiService: ApiService): CartRepository {
        return CartRepositoryImpl(
            apiService = apiService
        )
    }
}
