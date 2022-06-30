package com.example.feature_products_impl.domain.mapper

import com.example.core_database_api.models.ProductInListDTOSharedPrefs
import com.example.feature_products_impl.presentation.view_objects.ProductInListVO

fun ProductInListDTOSharedPrefs.toVO(): ProductInListVO =
    ProductInListVO(
        guid = guid,
        image = image,
        name = name,
        price = price,
        rating = rating,
        isFavorite = isFavorite,
        isInCart = isInCart,
        views = views
    )