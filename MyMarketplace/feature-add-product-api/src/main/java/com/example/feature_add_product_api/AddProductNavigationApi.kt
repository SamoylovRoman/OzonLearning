package com.example.feature_add_product_api

import androidx.fragment.app.Fragment

interface AddProductNavigationApi {
    fun navigateToProductList(fragment: Fragment)
    fun isFeatureClosed(fragment: Fragment): Boolean
}