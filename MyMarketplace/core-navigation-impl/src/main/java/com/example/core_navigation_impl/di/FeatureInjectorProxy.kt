package com.example.core_navigation_impl.di

import android.content.Context
import androidx.work.WorkManager
import com.example.core_database_impl.di.DaggerCoreDatabaseComponent
import com.example.core_network_impl.di.DaggerCoreNetworkComponent
import com.example.core_network_impl.di.DaggerCoreNetworkComponent_CoreNetworkDependenciesComponent
import com.example.feature_add_product_impl.di.AddProductFeatureComponent
import com.example.feature_add_product_impl.di.DaggerAddProductFeatureComponent_AddProductFeatureDependenciesComponent
import com.example.feature_pdp_impl.di.DaggerPDPFeatureComponent_PDPFeatureDependenciesComponent
import com.example.feature_pdp_impl.di.PDPFeatureComponent
import com.example.feature_products_impl.di.DaggerProductFeatureComponent_ProductFeatureDependenciesComponent
import com.example.feature_products_impl.di.ProductFeatureComponent

object FeatureInjectorProxy {
    fun initFeatureProductsDI(context: Context) {
        ProductFeatureComponent.initAndGet(
            DaggerProductFeatureComponent_ProductFeatureDependenciesComponent.builder()
                .networkApi(
                    DaggerCoreNetworkComponent.builder()
                        .workManager(WorkManager.getInstance(context))
                        .dependencies(
                            DaggerCoreNetworkComponent_CoreNetworkDependenciesComponent.builder()
                                .dataBaseApi(
                                    DaggerCoreDatabaseComponent.builder().context(context).build()
                                )
                                .build()
                        )
                        .build()
                )
                .dataBaseApi(DaggerCoreDatabaseComponent.builder().context(context).build())
                .productNavigationApi(
                    DaggerCoreNavigationComponent.builder().build().getProductNavigation()
                )
                .build()
        )
    }

    fun initFeaturePDPDI(context: Context) {
        PDPFeatureComponent.initAndGet(
            DaggerPDPFeatureComponent_PDPFeatureDependenciesComponent.builder()
                .networkApi(
                    DaggerCoreNetworkComponent.builder()
                        .workManager(WorkManager.getInstance(context))
                        .dependencies(
                            DaggerCoreNetworkComponent_CoreNetworkDependenciesComponent.builder()
                                .dataBaseApi(
                                    DaggerCoreDatabaseComponent.builder().context(context).build()
                                )
                                .build()
                        )
                        .build()
                )
                .dataBaseApi(DaggerCoreDatabaseComponent.builder().context(context).build())
                .pDPNavigationApi(
                    DaggerCoreNavigationComponent.builder().build().getPDPNavigation()
                )
                .build()
        )
    }

    fun initFeatureAddProductDI(context: Context) {
        AddProductFeatureComponent.initAndGet(
            DaggerAddProductFeatureComponent_AddProductFeatureDependenciesComponent.builder()
                .networkApi(
                    DaggerCoreNetworkComponent.builder()
                        .workManager(WorkManager.getInstance(context))
                        .dependencies(
                            DaggerCoreNetworkComponent_CoreNetworkDependenciesComponent.builder()
                                .dataBaseApi(
                                    DaggerCoreDatabaseComponent.builder().context(context).build()
                                )
                                .build()
                        )
                        .build()
                )
                .dataBaseApi(DaggerCoreDatabaseComponent.builder().context(context).build())
                .addProductNavigationApi(
                    DaggerCoreNavigationComponent.builder().build().getAddProductNavigation()
                )
                .build()
        )
    }
}