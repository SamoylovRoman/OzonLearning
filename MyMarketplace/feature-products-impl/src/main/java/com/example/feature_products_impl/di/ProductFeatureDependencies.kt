package com.example.feature_products_impl.di

import com.example.core_database_api.ProductsDataBaseApi
import com.example.feature_products_api.ProductNavigationApi

interface ProductFeatureDependencies {
    fun productsApi(): ProductsDataBaseApi
    fun productNavigationApi(): ProductNavigationApi
}