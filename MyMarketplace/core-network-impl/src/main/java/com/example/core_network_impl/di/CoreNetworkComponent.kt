package com.example.core_network_impl.di

import android.content.Context
import androidx.work.WorkManager
import com.example.core_database_api.DataBaseApi
import com.example.core_network_api.NetworkApi
import com.example.core_network_api.WorkerManager
import com.example.core_network_impl.data.repositories.WorkerRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ApiModule::class, WorkerModule::class],
    dependencies = [CoreNetworkDependencies::class]
)
interface CoreNetworkComponent : NetworkApi {

    fun getRepository(): WorkerRepository
    fun getWorkManager(): WorkerManager

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun workManager(workManager: WorkManager): Builder
        fun dependencies(deps: CoreNetworkDependencies): Builder
        fun build(): CoreNetworkComponent
    }

    companion object {
        @Volatile
        var coreNetworkComponent: CoreNetworkComponent? = null
            private set

        fun initAndGet(
            context: Context,
            coreNetworkDependencies: CoreNetworkDependencies
        ): CoreNetworkComponent? {
            synchronized(CoreNetworkComponent::class) {
                if (coreNetworkComponent == null) {
                    coreNetworkComponent = DaggerCoreNetworkComponent.builder()
                        .workManager(WorkManager.getInstance(context))
                        .dependencies(coreNetworkDependencies)
                        .build()
                }
            }
            return coreNetworkComponent
        }

        fun get(): CoreNetworkComponent? {
            if (coreNetworkComponent == null) {
                throw RuntimeException("You must call 'initAndGet(coreNetworkDependencies: CoreNetworkDependencies)' method")
            }
            return coreNetworkComponent
        }

        fun resetComponent() {
            coreNetworkComponent = null
        }
    }

    @Singleton
    @Component(dependencies = [DataBaseApi::class])
    interface CoreNetworkDependenciesComponent : CoreNetworkDependencies
}