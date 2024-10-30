package com.example.sample_app.di

import com.example.sample_app.core.data.repositories.interfaces.CartRepository
import com.example.sample_app.core.data.repositories.interfaces.ProductsRepository
import com.example.sample_app.presentations.cart.domain.usecases.GetCartsUseCase
import com.example.sample_app.presentations.cart.domain.usecases.GetCartsUseCaseImpl
import com.example.sample_app.presentations.products.domain.GetCategoriesUseCase
import com.example.sample_app.presentations.products.domain.GetCategoriesUseCaseImpl
import com.example.sample_app.presentations.products.domain.GetProductsUseCase
import com.example.sample_app.presentations.products.domain.GetProductsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetProductsUseCase(repository: ProductsRepository): GetProductsUseCase {
        return GetProductsUseCaseImpl(
            repository = repository
        )
    }

    @Provides
    @Singleton
    fun provideGetCategoriesUseCase(repository: ProductsRepository): GetCategoriesUseCase {
        return GetCategoriesUseCaseImpl(
            repository = repository
        )
    }

    @Provides
    @Singleton
    fun provideGetCartsUseCase(repository: CartRepository): GetCartsUseCase {
        return GetCartsUseCaseImpl(
            repository = repository
        )
    }
}
