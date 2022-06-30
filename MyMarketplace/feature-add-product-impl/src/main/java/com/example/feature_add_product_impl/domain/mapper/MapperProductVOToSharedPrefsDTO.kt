package com.example.feature_add_product_impl.domain.mapper

import com.example.core_database_api.models.ProductDTOSharedPrefs
import com.example.core_utils.getRandomImageLink
import com.example.feature_add_product_impl.presentation.view_objects.ProductVO

fun ProductVO.toSharedPrefsDTO(): ProductDTOSharedPrefs =
    ProductDTOSharedPrefs(
        guid = guid,
        name = name,
        price = price,
        description = description,
        rating = rating,
        isFavorite = false,
        isInCart = false,
        images = listOf(image ?: getRandomImageLink()),
        weight = 0.0,
        count = 0,
        availableCount = 0,
        additionalParams = emptyMap()
    )