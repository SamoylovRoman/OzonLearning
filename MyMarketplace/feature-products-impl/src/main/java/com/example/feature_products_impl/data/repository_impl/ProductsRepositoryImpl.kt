package com.example.feature_products_impl.data.repository_impl

import com.example.core_database_api.ProductsDataBaseApi
import com.example.core_database_api.models.ProductInListDTOSharedPrefs
import com.example.feature_products_impl.domain.repository.ProductsRepository
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(private val productsDataBaseApi: ProductsDataBaseApi) :
    ProductsRepository {

    override fun getProducts(): List<ProductInListDTOSharedPrefs> {
        return productsDataBaseApi.getProductsInList()
    }

    override fun incrementProductViews(guid: String) {
        productsDataBaseApi.incrementProductViews(guid)
    }
}