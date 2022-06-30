package com.example.feature_add_product_impl.presentation.view_models

import androidx.lifecycle.ViewModel

import com.example.feature_add_product_impl.domain.interactor.AddProductInteractor
import com.example.feature_add_product_impl.presentation.view_objects.ProductVO
import javax.inject.Inject

class AddProductViewModel @Inject constructor(private val addProductInteractor: AddProductInteractor) :
    ViewModel() {

    fun addNewProduct(newProduct: ProductVO): Boolean {
        return addProductInteractor.addNewProduct(newProduct)
    }
}