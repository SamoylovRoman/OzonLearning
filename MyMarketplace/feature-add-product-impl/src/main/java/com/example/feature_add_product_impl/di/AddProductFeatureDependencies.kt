package com.example.feature_add_product_impl.di

import com.example.core_database_api.ProductsDataBaseApi
import com.example.feature_add_product_api.AddProductNavigationApi

interface AddProductFeatureDependencies {
    fun productsApi(): ProductsDataBaseApi
    fun addProductNavigation(): AddProductNavigationApi
}