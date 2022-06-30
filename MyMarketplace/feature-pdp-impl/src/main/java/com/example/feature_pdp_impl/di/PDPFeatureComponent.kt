package com.example.feature_pdp_impl.di

import com.example.core_database_api.DataBaseApi
import com.example.core_network_api.NetworkApi
import com.example.core_utils.di.PerFeature
import com.example.feature_pdp_api.PDPNavigationApi
import com.example.feature_pdp_impl.presentation.view.PDPFragment
import dagger.Component
import java.lang.RuntimeException

@Component(
    modules = [InteractorModule::class, RepositoryModule::class],
    dependencies = [PDPFeatureDependencies::class]
)
@PerFeature
abstract class PDPFeatureComponent {

    companion object {

        @Volatile
        var pdpFeatureComponent: PDPFeatureComponent? = null
            private set

        fun initAndGet(pdpFeatureDependencies: PDPFeatureDependencies): PDPFeatureComponent? {
            if (pdpFeatureComponent == null) {
                synchronized(PDPFeatureComponent::class) {
                    pdpFeatureComponent = DaggerPDPFeatureComponent.builder()
                        .pDPFeatureDependencies(pdpFeatureDependencies)
                        .build()
                }
            }
            return pdpFeatureComponent
        }

        fun get(): PDPFeatureComponent? {
            if (pdpFeatureComponent == null) {
                throw RuntimeException("You must call 'initAndGet(productFeatureDependencies: ProductFeatureDependencies)' method")
            }
            return pdpFeatureComponent
        }

        fun resetComponent() {
            pdpFeatureComponent = null
        }

    }

    abstract fun inject(fragment: PDPFragment)

    @PerFeature
    @Component(dependencies = [NetworkApi::class, PDPNavigationApi::class, DataBaseApi::class])
    interface PDPFeatureDependenciesComponent : PDPFeatureDependencies
}