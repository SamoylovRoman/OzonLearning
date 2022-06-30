package com.example.feature_products_impl.domain.interactor

import com.example.feature_products_impl.presentation.view_objects.ProductInListVO

interface ProductsInteractor {
    fun getProducts(): List<ProductInListVO>
    fun incrementProductViews(guid: String)
}