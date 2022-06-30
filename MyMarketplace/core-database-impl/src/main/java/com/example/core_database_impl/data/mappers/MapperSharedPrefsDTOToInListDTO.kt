package com.example.core_database_impl.data.mappers

import com.example.core_database_api.models.ProductDTOSharedPrefs
import com.example.core_database_api.models.ProductInListDTOSharedPrefs

fun ProductDTOSharedPrefs.toProductInListDTOSharedPrefs(): ProductInListDTOSharedPrefs =
    ProductInListDTOSharedPrefs(
        guid = guid,
        image = images.first(),
        name = name,
        price = price,
        rating = rating,
        isFavorite = isFavorite,
        isInCart = isInCart,
        views = 0
    )