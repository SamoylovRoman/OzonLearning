package com.example.feature_pdp_impl.presentation.view_objects

data class ProductVO(
    val guid: String,
    val name: String,
    val price: String,
    val description: String,
    val rating: Double,
    val image: String?
)
