package com.example.feature_products_impl.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.feature_products_impl.domain.interactor.ProductsInteractor
import com.example.feature_products_impl.presentation.view_objects.ProductInListVO

class ProductsViewModel(private val interactor: ProductsInteractor) : ViewModel() {
    private val _productsInList = MutableLiveData<List<ProductInListVO>>()

    val productsInList: LiveData<List<ProductInListVO>>
        get() = _productsInList

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun incrementViewsCount(productId: String) {
        interactor.incrementProductViews(productId)
        _productsInList.postValue(interactor.getProducts())
    }

    fun loadProductsList() {
        _isLoading.postValue(true)
        _productsInList.postValue(interactor.getProducts())
        _isLoading.postValue(false)
    }
}