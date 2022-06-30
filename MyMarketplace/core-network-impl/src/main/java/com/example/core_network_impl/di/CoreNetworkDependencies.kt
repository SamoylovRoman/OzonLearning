package com.example.core_network_impl.di

import com.example.core_database_api.ProductsDataBaseApi

interface CoreNetworkDependencies {
    fun getDataBase(): ProductsDataBaseApi
}