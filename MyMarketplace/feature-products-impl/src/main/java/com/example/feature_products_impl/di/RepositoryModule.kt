package com.example.feature_products_impl.di

import com.example.feature_products_impl.data.repository_impl.ProductsRepositoryImpl
import com.example.feature_products_impl.domain.repository.ProductsRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindProductsRepository(repository: ProductsRepositoryImpl): ProductsRepository
}