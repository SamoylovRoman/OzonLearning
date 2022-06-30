package com.example.core_network_api

interface NetworkApi {
    fun getProductApi(): ProductsApi
    fun getWorkerManager(): WorkerManager
}