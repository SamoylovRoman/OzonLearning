package com.example.feature_add_product_impl.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.core_utils.*
import com.example.core_utils.NavigationBundleArguments.ADDED_NEW_PRODUCT_ARG
import com.example.core_utils.NavigationBundleArguments.REQUEST_ADD_NEW_PRODUCT
import com.example.feature_add_product_api.AddProductNavigationApi
import com.example.feature_add_product_impl.databinding.FragmentAddProductBinding
import com.example.feature_add_product_impl.di.AddProductFeatureComponent
import com.example.feature_add_product_impl.domain.interactor.AddProductInteractor
import com.example.feature_add_product_impl.presentation.view_models.AddProductViewModel
import com.example.feature_add_product_impl.presentation.view_objects.ProductVO
import javax.inject.Inject
import kotlin.random.Random

class AddProductFragment : Fragment() {

    private lateinit var binding: FragmentAddProductBinding

    @Inject
    lateinit var productsInteractor: AddProductInteractor

    @Inject
    lateinit var addProductNavigationApi: AddProductNavigationApi

    private val addProductViewModel: AddProductViewModel by viewModelCreator {
        AddProductViewModel(productsInteractor)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AddProductFeatureComponent.addProductFeatureComponent?.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    override fun onPause() {
        if (isRemoving) {
            if (addProductNavigationApi.isFeatureClosed(this)) {
                AddProductFeatureComponent.resetComponent()
            }
        }
        super.onPause()
    }

    private fun initListeners() {
        binding.toFillButton.setOnClickListener {
            fillAllValues()
        }
        binding.toClearButton.setOnClickListener {
            clearAllValues()
        }
        binding.toSaveButton.setOnClickListener {
            if (checkInputValues()) {
                val isAddedProduct = createAndAddProduct()
                setFragmentResult(
                    REQUEST_ADD_NEW_PRODUCT,
                    bundleOf(ADDED_NEW_PRODUCT_ARG to isAddedProduct)
                )
                addProductNavigationApi.navigateToProductList(this)
            } else {
                toast(R.string.text_no_all_values)
            }
        }
    }

    private fun checkInputValues(): Boolean {
        with(binding) {
            return (productImageLinkInput.text!!.isNotBlank() &&
                    productNameInput.text!!.isNotBlank() &&
                    productPriceInput.text!!.isNotBlank() &&
                    productRatingInput.text!!.isNotBlank())
        }
    }

    private fun createAndAddProduct(): Boolean {
        return addProductViewModel.addNewProduct(
            ProductVO(
                guid = Random.nextLong().toString(),
                name = binding.productNameInput.text.toString(),
                price = binding.productPriceInput.text.toString(),
                rating = binding.productRatingInput.text.toString().toDouble(),
                description = binding.productDescriptionInput.text.toString(),
                image = binding.productImageLinkInput.text.toString()
            )
        )
    }

    private fun fillAllValues() {
        with(binding) {
            productImageLinkInput.setText(getRandomImageLink())
            productNameInput.setText(getRandomProductName())
            productPriceInput.setText(Random.nextInt(MAX_PRICE_VALUE).toString())
            productRatingInput.setText(Random.nextDouble(MAX_RATING_VALUE).toString())
            productDescriptionInput.setText(getRandomDescription())
        }
    }

    private fun clearAllValues() {
        with(binding) {
            productImageLinkInput.text?.clear()
            productNameInput.text?.clear()
            productPriceInput.text?.clear()
            productRatingInput.text?.clear()
            productDescriptionInput.text?.clear()
        }
    }

    companion object {
        private const val MAX_PRICE_VALUE = 10000
        private const val MAX_RATING_VALUE = 5.0
    }
}