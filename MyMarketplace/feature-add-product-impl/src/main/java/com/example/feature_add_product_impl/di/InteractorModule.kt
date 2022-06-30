package com.example.feature_add_product_impl.di

import com.example.feature_add_product_impl.domain.interactor.AddProductInteractor
import com.example.feature_add_product_impl.domain.interactor.AddProductInteractorImpl
import dagger.Binds
import dagger.Module

@Module
interface InteractorModule {

    @Binds
    fun bindProductsInteractor(interactor: AddProductInteractorImpl): AddProductInteractor
}