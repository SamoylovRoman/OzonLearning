package com.example.feature_add_product_impl.domain.interactor

import com.example.feature_add_product_impl.domain.mapper.toSharedPrefsDTO
import com.example.feature_add_product_impl.domain.repository.AddProductRepository
import com.example.feature_add_product_impl.presentation.view_objects.ProductVO
import javax.inject.Inject

class AddProductInteractorImpl @Inject constructor(private val repository: AddProductRepository) :
    AddProductInteractor {
    override fun addNewProduct(newProduct: ProductVO): Boolean {
        return repository.addNewProduct(newProduct = newProduct.toSharedPrefsDTO())
    }
}