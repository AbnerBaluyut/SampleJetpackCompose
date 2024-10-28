package com.example.sample_app.di

import com.example.sample_app.presentations.products.data.remote.ProductsRemoteSource
import com.example.sample_app.presentations.products.data.repository.ProductsRepositoryImpl
import com.example.sample_app.presentations.products.domain.repository.ProductsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun bindProductsRepository(remoteSource: ProductsRemoteSource) : ProductsRepository {
        return ProductsRepositoryImpl(
            remoteSource = remoteSource
        )
    }
}
