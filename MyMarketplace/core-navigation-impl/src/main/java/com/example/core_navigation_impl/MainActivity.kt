package com.example.core_navigation_impl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.core_navigation_impl.di.FeatureInjectorProxy
import com.example.feature_products_impl.presentation.view.ProductsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigateToProductsFragment()
    }

    private fun navigateToProductsFragment() {
        FeatureInjectorProxy.initFeatureProductsDI(context = applicationContext)
        val newFragment = ProductsFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, newFragment, ProductsFragment::class.java.simpleName)
            .addToBackStack(null)
            .commit()
    }
}