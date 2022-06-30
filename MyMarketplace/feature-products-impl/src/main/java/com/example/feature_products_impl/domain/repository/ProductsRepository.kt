package com.example.feature_products_impl.domain.repository

import com.example.core_database_api.models.ProductInListDTOSharedPrefs

interface ProductsRepository {
    fun getProducts(): List<ProductInListDTOSharedPrefs>
    fun incrementProductViews(guid: String)
}