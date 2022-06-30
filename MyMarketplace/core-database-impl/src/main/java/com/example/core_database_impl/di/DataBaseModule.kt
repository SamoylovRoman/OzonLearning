package com.example.core_database_impl.di

import com.example.core_database_api.ProductsDataBaseApi
import com.example.core_database_impl.data.ProductsDataBaseApiImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DataBaseModule {

    @Singleton
    @Binds
    fun bindProductsDataBaseApi(api: ProductsDataBaseApiImpl): ProductsDataBaseApi
}