package com.example.feature_add_product_impl.domain.interactor

import com.example.feature_add_product_impl.presentation.view_objects.ProductVO

interface AddProductInteractor {
    fun addNewProduct(newProduct: ProductVO): Boolean
}