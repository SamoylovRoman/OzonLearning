package com.example.feature_add_product_impl.domain.repository

import com.example.core_database_api.models.ProductDTOSharedPrefs

interface AddProductRepository {
fun addNewProduct(newProduct: ProductDTOSharedPrefs): Boolean
}