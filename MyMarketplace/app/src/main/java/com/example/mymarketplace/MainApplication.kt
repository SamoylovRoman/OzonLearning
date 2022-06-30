package com.example.mymarketplace

import android.app.Application
import androidx.work.WorkManager
import com.example.core_database_impl.di.DaggerCoreDatabaseComponent
import com.example.core_network_impl.di.DaggerCoreNetworkComponent
import com.example.core_network_impl.di.DaggerCoreNetworkComponent_CoreNetworkDependenciesComponent

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val coreNetworkComponent = DaggerCoreNetworkComponent.builder()
            .workManager(WorkManager.getInstance(applicationContext))
            .dependencies(
                DaggerCoreNetworkComponent_CoreNetworkDependenciesComponent.builder()
                    .dataBaseApi(
                        DaggerCoreDatabaseComponent.builder().context(applicationContext).build()
                    )
                    .build()
            )
            .build()
        coreNetworkComponent.getWorkManager().startWorkers()
    }
}