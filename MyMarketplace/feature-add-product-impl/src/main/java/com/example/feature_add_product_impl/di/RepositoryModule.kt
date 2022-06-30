package com.example.feature_add_product_impl.di

import com.example.feature_add_product_impl.data.repository_impl.AddProductRepositoryImpl
import com.example.feature_add_product_impl.domain.repository.AddProductRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindAddProductRepository(repository: AddProductRepositoryImpl): AddProductRepository
}