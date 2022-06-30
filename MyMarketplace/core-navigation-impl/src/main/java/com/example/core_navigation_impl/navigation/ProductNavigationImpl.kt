package com.example.core_navigation_impl.navigation

import androidx.fragment.app.Fragment
import com.example.core_navigation_impl.R
import com.example.core_navigation_impl.di.FeatureInjectorProxy
import com.example.feature_add_product_impl.presentation.view.AddProductFragment
import com.example.feature_pdp_impl.presentation.view.PDPFragment
import com.example.feature_products_api.ProductNavigationApi
import com.example.feature_products_impl.presentation.view.ProductsFragment
import javax.inject.Inject

class ProductNavigationImpl @Inject constructor() : ProductNavigationApi {

    override fun navigateToPDP(fragment: Fragment, guid: String) {
        FeatureInjectorProxy.initFeaturePDPDI(fragment.requireContext())
        val newFragment = PDPFragment.newInstance(guid)
        fragment.activity
            ?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.fragmentContainer, newFragment, PDPFragment::class.java.simpleName)
            ?.addToBackStack(null)
            ?.commit()
    }

    override fun navigateToAddProduct(fragment: Fragment) {
        FeatureInjectorProxy.initFeatureAddProductDI(fragment.requireContext())
        val newFragment = AddProductFragment()
        fragment.activity
            ?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(
                R.id.fragmentContainer,
                newFragment,
                AddProductFragment::class.java.simpleName
            )
            ?.addToBackStack(null)
            ?.commit()
    }

    override fun isFeatureClosed(fragment: Fragment): Boolean {
        return if (fragment.javaClass.simpleName != ProductsFragment::class.simpleName) {
            fragment.activity?.supportFragmentManager?.findFragmentByTag(ProductsFragment::class.java.simpleName) == null
        } else {
            true
        }
    }
}