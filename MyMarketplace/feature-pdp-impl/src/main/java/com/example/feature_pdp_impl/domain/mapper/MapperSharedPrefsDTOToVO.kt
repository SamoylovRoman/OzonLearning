package com.example.feature_pdp_impl.domain.mapper

import com.example.core_database_api.models.ProductDTOSharedPrefs
import com.example.feature_pdp_impl.presentation.view_objects.ProductVO

fun ProductDTOSharedPrefs.toVO(): ProductVO =
    ProductVO(
        guid = guid,
        name = name,
        price = price,
        description = description,
        rating = rating,
        image = images.firstOrNull()
    )