package com.example.core_database_api

import com.example.core_database_api.models.ProductDTOSharedPrefs
import com.example.core_database_api.models.ProductInListDTOSharedPrefs

interface ProductsDataBaseApi {
    fun addProductsInList(list: List<ProductInListDTOSharedPrefs>)
    fun getProductsInList(): List<ProductInListDTOSharedPrefs>
    fun addProducts(list: List<ProductDTOSharedPrefs>)
    fun getProducts(): List<ProductDTOSharedPrefs>
    fun getProductById(guid: String): ProductDTOSharedPrefs?
    fun incrementProductViews(guid: String)
    fun addNewProduct(newProduct: ProductDTOSharedPrefs): Boolean
}