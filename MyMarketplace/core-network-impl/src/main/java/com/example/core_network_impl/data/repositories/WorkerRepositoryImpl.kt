package com.example.core_network_impl.data.repositories

import com.example.core_database_api.ProductsDataBaseApi
import com.example.core_network_api.ProductsApi
import com.example.core_network_impl.data.mappers.toProductDTOSharedPrefs
import com.example.core_network_impl.data.mappers.toProductInListDTOSharedPrefs
import javax.inject.Inject

class WorkerRepositoryImpl @Inject constructor(
    private val productsApi: ProductsApi,
    private val productsDataBaseApi: ProductsDataBaseApi
) : WorkerRepository {
    override fun getProductsInList() {
        productsApi.getProductsInList().execute().body()?.also { productsInListDTO ->
            productsDataBaseApi.addProductsInList(
                productsInListDTO.map { productInListDTO ->
                    productInListDTO.toProductInListDTOSharedPrefs()
                }
            )
        }
    }

    override fun getProducts() {
        productsApi.getProducts().execute().body()?.also { productsDTO ->
            productsDataBaseApi.addProducts(
                productsDTO.map { productDTO ->
                    productDTO.toProductDTOSharedPrefs()
                }
            )
        }
    }
}

interface WorkerRepository {
    fun getProductsInList()
    fun getProducts()
}