package com.example.feature_pdp_impl.data.repository_impl

import com.example.core_database_api.ProductsDataBaseApi
import com.example.core_database_api.models.ProductDTOSharedPrefs
import com.example.feature_pdp_impl.domain.repository.PDPRepository
import javax.inject.Inject

class PDPRepositoryImpl @Inject constructor(private val productsDataBaseApi: ProductsDataBaseApi) :
    PDPRepository {
    override fun getProductById(guid: String): ProductDTOSharedPrefs? {
        return productsDataBaseApi.getProductById(guid)
    }
}