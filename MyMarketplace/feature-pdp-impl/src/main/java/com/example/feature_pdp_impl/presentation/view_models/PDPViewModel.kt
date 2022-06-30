package com.example.feature_pdp_impl.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.feature_pdp_impl.domain.interactor.PDPInteractor
import com.example.feature_pdp_impl.presentation.view_objects.ProductVO
import javax.inject.Inject

class PDPViewModel @Inject constructor(private val pdpInteractor: PDPInteractor) : ViewModel() {
    private val _detailProduct = MutableLiveData<ProductVO>()
    val detailProduct: LiveData<ProductVO>
        get() = _detailProduct

    fun getProductInfo(productId: String?) {
        productId?.let { _detailProduct.postValue(pdpInteractor.getProductById(productId)) }
    }
}