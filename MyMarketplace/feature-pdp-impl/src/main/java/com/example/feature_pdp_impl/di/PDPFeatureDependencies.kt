package com.example.feature_pdp_impl.di

import com.example.core_database_api.ProductsDataBaseApi
import com.example.feature_pdp_api.PDPNavigationApi

interface PDPFeatureDependencies {
    fun productsApi(): ProductsDataBaseApi
    fun pdpNavigation(): PDPNavigationApi
}