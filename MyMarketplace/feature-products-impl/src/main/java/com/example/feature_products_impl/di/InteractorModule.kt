package com.example.feature_products_impl.di

import com.example.feature_products_impl.domain.interactor.ProductsInteractor
import com.example.feature_products_impl.domain.interactor.ProductsInteractorImpl
import dagger.Binds
import dagger.Module

@Module
interface InteractorModule {

    @Binds
    fun bindProductsInteractor(interactor: ProductsInteractorImpl): ProductsInteractor
}