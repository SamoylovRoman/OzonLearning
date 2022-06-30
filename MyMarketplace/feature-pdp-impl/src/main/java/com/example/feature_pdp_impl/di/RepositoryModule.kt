package com.example.feature_pdp_impl.di

import com.example.feature_pdp_impl.data.repository_impl.PDPRepositoryImpl
import com.example.feature_pdp_impl.domain.repository.PDPRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindPDPRepository(repository: PDPRepositoryImpl): PDPRepository
}