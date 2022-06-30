package com.example.core_network_impl.data.mappers

import com.example.core_database_api.models.ProductDTOSharedPrefs
import com.example.core_database_api.models.ProductInListDTOSharedPrefs
import com.example.core_network_api.models.ProductDTO
import com.example.core_network_api.models.ProductInListDTO

fun ProductInListDTO.toProductInListDTOSharedPrefs(): ProductInListDTOSharedPrefs {
    return ProductInListDTOSharedPrefs(
        guid = guid,
        image = image,
        name = name,
        price = price,
        rating = rating,
        isFavorite = isFavorite,
        isInCart = isInCart,
        views = views
    )
}

fun ProductDTO.toProductDTOSharedPrefs(): ProductDTOSharedPrefs {
    return ProductDTOSharedPrefs(
        guid = guid,
        name = name,
        price = price,
        description = description,
        rating = rating,
        isFavorite = false,
        isInCart = false,
        images = images,
        weight = weight,
        count = count,
        availableCount = availableCount,
        additionalParams = additionalParams
    )
}