package com.example.feature_add_product_impl.di

import com.example.core_database_api.DataBaseApi
import com.example.core_network_api.NetworkApi
import com.example.core_utils.di.PerFeature
import com.example.feature_add_product_api.AddProductNavigationApi
import com.example.feature_add_product_impl.presentation.view.AddProductFragment
import dagger.Component
import java.lang.RuntimeException

@Component(
    modules = [InteractorModule::class, RepositoryModule::class],
    dependencies = [AddProductFeatureDependencies::class]
)
@PerFeature
abstract class AddProductFeatureComponent {

    companion object {

        @Volatile
        var addProductFeatureComponent: AddProductFeatureComponent? = null
            private set

        fun initAndGet(addProductFeatureDependencies: AddProductFeatureDependencies): AddProductFeatureComponent? {
            if (addProductFeatureComponent == null) {
                synchronized(AddProductFeatureComponent::class) {
                    addProductFeatureComponent = DaggerAddProductFeatureComponent.builder()
                        .addProductFeatureDependencies(addProductFeatureDependencies)
                        .build()
                }
            }
            return addProductFeatureComponent
        }

        fun get(): AddProductFeatureComponent? {
            if (addProductFeatureComponent == null) {
                throw RuntimeException("You must call 'initAndGet(addProductFeatureDependencies: AddProductFeatureDependencies)' method")
            }
            return addProductFeatureComponent
        }

        fun resetComponent() {
            addProductFeatureComponent = null
        }

    }

    abstract fun inject(fragment: AddProductFragment)

    @PerFeature
    @Component(dependencies = [NetworkApi::class, AddProductNavigationApi::class, DataBaseApi::class])
    interface AddProductFeatureDependenciesComponent : AddProductFeatureDependencies
}