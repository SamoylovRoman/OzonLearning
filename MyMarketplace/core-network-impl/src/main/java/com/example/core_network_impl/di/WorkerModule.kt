package com.example.core_network_impl.di

import com.example.core_network_api.WorkerManager
import com.example.core_network_impl.data.repositories.WorkerManagerImpl
import com.example.core_network_impl.data.repositories.WorkerRepository
import com.example.core_network_impl.data.repositories.WorkerRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface WorkerModule {

    @Binds
    @Singleton
    fun bindWorkerManager(repository: WorkerManagerImpl): WorkerManager

    @Binds
    @Singleton
    fun bindWorkerRepository(repositoryImpl: WorkerRepositoryImpl): WorkerRepository
}