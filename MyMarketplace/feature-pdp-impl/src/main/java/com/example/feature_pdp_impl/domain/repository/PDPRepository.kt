package com.example.feature_pdp_impl.domain.repository

import com.example.core_database_api.models.ProductDTOSharedPrefs

interface PDPRepository {
    fun getProductById(guid: String): ProductDTOSharedPrefs?
}