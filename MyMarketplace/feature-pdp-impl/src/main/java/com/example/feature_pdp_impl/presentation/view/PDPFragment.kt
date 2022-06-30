package com.example.feature_pdp_impl.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.core_utils.viewModelCreator
import com.example.feature_pdp_api.PDPNavigationApi
import com.example.core_utils.R
import com.example.feature_pdp_impl.databinding.FragmentPDPBinding
import com.example.feature_pdp_impl.di.PDPFeatureComponent
import com.example.feature_pdp_impl.domain.interactor.PDPInteractor
import com.example.feature_pdp_impl.presentation.view_models.PDPViewModel
import com.example.feature_pdp_impl.presentation.view_objects.ProductVO
import javax.inject.Inject

class PDPFragment : Fragment() {

    companion object {
        private const val PRODUCT_ID = "productId"

        @JvmStatic
        fun newInstance(productId: String) =
            PDPFragment().apply {
                arguments = Bundle().apply {
                    putString(PRODUCT_ID, productId)
                }
            }
    }

    private var productId: String? = null
    private lateinit var binding: FragmentPDPBinding

    @Inject
    lateinit var productsInteractor: PDPInteractor

    @Inject
    lateinit var pdpNavigationApi: PDPNavigationApi

    private val pdpViewModel: PDPViewModel by viewModelCreator {
        PDPViewModel(productsInteractor)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        PDPFeatureComponent.pdpFeatureComponent?.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            productId = it.getString(PRODUCT_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPDPBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()
        getProductInfo()
    }

    override fun onPause() {
        if (isRemoving) {
            if (pdpNavigationApi.isFeatureClosed(this)) {
                PDPFeatureComponent.resetComponent()
            }
        }
        super.onPause()
    }

    private fun observeLiveData() {
        pdpViewModel.detailProduct.observe(viewLifecycleOwner) { product ->
            setProductInfo(product)
        }
    }

    private fun getProductInfo() {
        pdpViewModel.getProductInfo(productId)
    }

    private fun setProductInfo(product: ProductVO) {
        with(binding) {
            Glide.with(root)
                .load(product.image)
                .placeholder(R.drawable.ic_download)
                .error(R.drawable.ic_error)
                .into(productImage)
            productDescriptionText.text = product.description
            productPriceText.text = product.price
            productRating.rating = product.rating.toFloat()
            productNameText.text = product.name
        }
    }
}