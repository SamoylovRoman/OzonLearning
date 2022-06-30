package com.example.feature_products_impl.di

import com.example.core_database_api.DataBaseApi
import com.example.core_network_api.NetworkApi
import com.example.core_utils.di.PerFeature
import com.example.feature_products_api.ProductNavigationApi
import com.example.feature_products_impl.presentation.view.ProductsFragment
import dagger.Component
import java.lang.RuntimeException

@Component(
    modules = [InteractorModule::class, RepositoryModule::class],
    dependencies = [ProductFeatureDependencies::class]
)
@PerFeature
abstract class ProductFeatureComponent {

    companion object {

        @Volatile
        var productFeatureComponent: ProductFeatureComponent? = null
            private set

        fun initAndGet(productFeatureDependencies: ProductFeatureDependencies): ProductFeatureComponent? {
            if (productFeatureComponent == null) {
                synchronized(ProductFeatureComponent::class) {
                    productFeatureComponent = DaggerProductFeatureComponent.builder()
                        .productFeatureDependencies(productFeatureDependencies)
                        .build()
                }
            }
            return productFeatureComponent
        }

        fun get(): ProductFeatureComponent? {
            if (productFeatureComponent == null) {
                throw RuntimeException("You must call 'initAndGet(productFeatureDependencies: ProductFeatureDependencies)' method")
            }
            return productFeatureComponent
        }

        fun resetComponent() {
            productFeatureComponent = null
        }

    }

    abstract fun inject(fragment: ProductsFragment)

    @PerFeature
    @Component(dependencies = [NetworkApi::class, ProductNavigationApi::class, DataBaseApi::class])
    interface ProductFeatureDependenciesComponent : ProductFeatureDependencies
}