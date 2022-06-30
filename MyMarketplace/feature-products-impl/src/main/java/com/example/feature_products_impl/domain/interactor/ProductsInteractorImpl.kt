package com.example.feature_products_impl.domain.interactor

import com.example.feature_products_impl.domain.mapper.toVO
import com.example.feature_products_impl.domain.repository.ProductsRepository
import com.example.feature_products_impl.presentation.view_objects.ProductInListVO
import javax.inject.Inject

class ProductsInteractorImpl @Inject constructor(private val repository: ProductsRepository) :
    ProductsInteractor {

    override fun getProducts(): List<ProductInListVO> {
        return repository.getProducts().map { productInListDTOSharedPrefs ->
            productInListDTOSharedPrefs.toVO()
        }
    }

    override fun incrementProductViews(guid: String) {
        repository.incrementProductViews(guid)
    }
}