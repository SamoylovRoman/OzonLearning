package com.example.feature_add_product_impl.data.repository_impl

import com.example.core_database_api.ProductsDataBaseApi
import com.example.core_database_api.models.ProductDTOSharedPrefs
import com.example.feature_add_product_impl.domain.repository.AddProductRepository
import javax.inject.Inject

class AddProductRepositoryImpl @Inject constructor(private val productsApi: ProductsDataBaseApi) :
    AddProductRepository {

    override fun addNewProduct(newProduct: ProductDTOSharedPrefs): Boolean {
        return productsApi.addNewProduct(newProduct)
    }

}