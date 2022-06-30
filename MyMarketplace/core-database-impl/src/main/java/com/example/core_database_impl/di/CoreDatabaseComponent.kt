package com.example.core_database_impl.di

import android.content.Context
import com.example.core_database_api.DataBaseApi
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataBaseModule::class])
interface CoreDatabaseComponent : DataBaseApi {

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun context(context: Context): Builder
        fun build():CoreDatabaseComponent
    }

    companion object {
        @Volatile
        var coreDatabaseComponent: CoreDatabaseComponent? = null
            private set

        fun initAndGet(context: Context): CoreDatabaseComponent? {
            if (coreDatabaseComponent == null) {
                synchronized(CoreDatabaseComponent::class) {
                    coreDatabaseComponent = DaggerCoreDatabaseComponent.builder().context(context).build()
                }
            }
            return coreDatabaseComponent
        }

        fun get(): CoreDatabaseComponent? {
            if (coreDatabaseComponent == null) {
                throw RuntimeException("You must call 'initAndGet(coreNetworkDependencies: CoreNetworkDependencies)' method")
            }
            return coreDatabaseComponent
        }

        fun resetComponent() {
            coreDatabaseComponent = null
        }
    }
}