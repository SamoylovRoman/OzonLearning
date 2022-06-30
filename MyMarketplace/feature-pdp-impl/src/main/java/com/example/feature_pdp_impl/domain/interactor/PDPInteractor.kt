package com.example.feature_pdp_impl.domain.interactor

import com.example.feature_pdp_impl.presentation.view_objects.ProductVO

interface PDPInteractor {
    fun getProductById(guid: String): ProductVO?
}