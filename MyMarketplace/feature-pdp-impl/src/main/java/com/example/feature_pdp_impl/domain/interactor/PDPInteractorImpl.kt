package com.example.feature_pdp_impl.domain.interactor

import com.example.feature_pdp_impl.domain.mapper.toVO
import com.example.feature_pdp_impl.domain.repository.PDPRepository
import com.example.feature_pdp_impl.presentation.view_objects.ProductVO
import javax.inject.Inject

class PDPInteractorImpl @Inject constructor(private val repository: PDPRepository) :
    PDPInteractor {

    override fun getProductById(guid: String): ProductVO? {
        return repository.getProductById(guid)?.toVO()
    }
}