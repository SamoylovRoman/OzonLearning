package com.example.feature_products_impl.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.WorkManager
import com.example.core_network_api.Key
import com.example.core_utils.NavigationBundleArguments.ADDED_NEW_PRODUCT_ARG
import com.example.core_utils.NavigationBundleArguments.REQUEST_ADD_NEW_PRODUCT
import com.example.core_utils.viewModelCreator
import com.example.feature_products_api.ProductNavigationApi
import com.example.feature_products_impl.databinding.FragmentProductsBinding
import com.example.feature_products_impl.di.ProductFeatureComponent
import com.example.feature_products_impl.domain.interactor.ProductsInteractor
import com.example.feature_products_impl.presentation.view.adapters.ProductListAdapter
import com.example.feature_products_impl.presentation.view_models.ProductsViewModel
import javax.inject.Inject

class ProductsFragment : Fragment() {

    private lateinit var binding: FragmentProductsBinding
    private lateinit var productAdapter: ProductListAdapter

    @Inject
    lateinit var productsInteractor: ProductsInteractor

    @Inject
    lateinit var productNavigationApi: ProductNavigationApi

    private val productsViewModel: ProductsViewModel by viewModelCreator {
        ProductsViewModel(productsInteractor)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ProductFeatureComponent.productFeatureComponent?.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initProductsList()
        initListeners()
        observeLiveData()
        observeWorkerState()
    }

    private fun observeWorkerState() {
        WorkManager.getInstance(requireContext())
            .getWorkInfosByTagLiveData(Key.TAG_PRODUCTS_IN_LIST_REQUEST)
            .observe(viewLifecycleOwner) { workInfo ->
                if (workInfo != null && workInfo.isNotEmpty() && workInfo.first().state.isFinished) {
                    productsViewModel.loadProductsList()
                }
            }
    }

    override fun onPause() {
        if (isRemoving) {
            if (productNavigationApi.isFeatureClosed(this)) {
                ProductFeatureComponent.resetComponent()
            }
        }
        super.onPause()
    }

    private fun initProductsList() {
        productAdapter =
            ProductListAdapter { idProduct ->
                productsViewModel.incrementViewsCount(idProduct)
                productNavigationApi.navigateToPDP(fragment = this, idProduct)
            }
        with(binding.productList) {
            adapter = productAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    private fun initListeners() {
        binding.addFloatButton.setOnClickListener {
            productNavigationApi.navigateToAddProduct(fragment = this)
            setFragmentResultListener(REQUEST_ADD_NEW_PRODUCT) { _, bundle ->
                if (bundle.getBoolean(ADDED_NEW_PRODUCT_ARG)) {
                    productsViewModel.loadProductsList()
                }
            }
        }
    }

    private fun observeLiveData() {
        productsViewModel.productsInList.observe(viewLifecycleOwner) { newList ->
            productAdapter.submitList(newList)
        }
        productsViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            toggleProgress(isLoading)
        }
    }

    private fun toggleProgress(isVisible: Boolean) {
        with(binding) {
            progressBar.isVisible = isVisible
            productList.isVisible = !isVisible
            addFloatButton.isVisible = !isVisible
        }
    }
}