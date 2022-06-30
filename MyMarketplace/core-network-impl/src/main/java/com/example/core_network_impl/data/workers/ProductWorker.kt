package com.example.core_network_impl.data.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.core_database_impl.di.CoreDatabaseComponent
import com.example.core_network_impl.di.CoreNetworkComponent
import com.example.core_network_impl.di.DaggerCoreNetworkComponent_CoreNetworkDependenciesComponent

class ProductWorker(context: Context, workerParams: WorkerParameters) : Worker(
    context,
    workerParams
) {

private val workerRepository = CoreNetworkComponent.initAndGet(
    context,
    DaggerCoreNetworkComponent_CoreNetworkDependenciesComponent.builder()
        .dataBaseApi(CoreDatabaseComponent.initAndGet(context)).build()
)!!
    .getRepository()

    override fun doWork(): Result {
        return try {
            workerRepository.getProducts()
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}