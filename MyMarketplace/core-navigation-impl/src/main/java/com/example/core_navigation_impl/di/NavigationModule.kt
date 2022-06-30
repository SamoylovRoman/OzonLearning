package com.example.core_navigation_impl.di

import com.example.core_navigation_impl.navigation.AddProductNavigationImpl
import com.example.core_navigation_impl.navigation.PDPNavigationImpl
import com.example.core_navigation_impl.navigation.ProductNavigationImpl
import com.example.feature_add_product_api.AddProductNavigationApi
import com.example.feature_pdp_api.PDPNavigationApi
import com.example.feature_products_api.ProductNavigationApi
import dagger.Binds
import dagger.Module

@Module
interface NavigationModule {

    @Binds
    fun bindProductNavigation(navigation: ProductNavigationImpl): ProductNavigationApi

    @Binds
    fun bindPDPNavigation(navigation: PDPNavigationImpl): PDPNavigationApi

    @Binds
    fun bindAddProductNavigation(navigation: AddProductNavigationImpl): AddProductNavigationApi
}