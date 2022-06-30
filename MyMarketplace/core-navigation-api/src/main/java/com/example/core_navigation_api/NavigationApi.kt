package com.example.core_navigation_api

import com.example.feature_add_product_api.AddProductNavigationApi
import com.example.feature_pdp_api.PDPNavigationApi
import com.example.feature_products_api.ProductNavigationApi

interface NavigationApi {
    fun getProductNavigation(): ProductNavigationApi
    fun getPDPNavigation(): PDPNavigationApi
    fun getAddProductNavigation(): AddProductNavigationApi
}