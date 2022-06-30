package com.example.core_network_impl.data.repositories

import androidx.work.*
import com.example.core_network_api.Key
import com.example.core_network_api.WorkerManager
import com.example.core_network_impl.data.workers.ProductInListWorker
import com.example.core_network_impl.data.workers.ProductWorker
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class WorkerManagerImpl @Inject constructor(private val workManager: WorkManager) : WorkerManager {
    override fun startWorkers() {
        val productsInListRequest = OneTimeWorkRequest.Builder(ProductInListWorker::class.java)
            .setBackoffCriteria(BackoffPolicy.LINEAR, BACKOFF_DELAY, TimeUnit.SECONDS)
            .setConstraints(
                Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
            )
            .addTag(Key.TAG_PRODUCTS_IN_LIST_REQUEST)
            .build()
        val productsRequest = OneTimeWorkRequest.Builder(ProductWorker::class.java)
            .setConstraints(
                Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
            )
            .build()
        workManager.beginUniqueWork(
            UNIQUE_WORK,
            ExistingWorkPolicy.KEEP,
            productsInListRequest
        ).then(productsRequest).enqueue()

    }

    companion object {
        private const val BACKOFF_DELAY = 10L
        private const val UNIQUE_WORK = "Products unique work"
    }
}