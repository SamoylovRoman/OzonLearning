package com.example.core_database_impl.data

import android.content.Context
import com.example.core_database_api.ProductsDataBaseApi
import com.example.core_database_api.models.ProductDTOSharedPrefs
import com.example.core_database_api.models.ProductInListDTOSharedPrefs
import com.example.core_database_impl.data.mappers.toProductInListDTOSharedPrefs
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class ProductsDataBaseApiImpl @Inject constructor(context: Context) : ProductsDataBaseApi {

    private val prefs = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    override fun addProductsInList(list: List<ProductInListDTOSharedPrefs>) {
        val currentProductsList = getProductsInList().toMutableList()
        currentProductsList.addAll(
            list.filter { x -> currentProductsList.find { it.guid == x.guid } == null }
        )
        prefs.edit()
            .putString(PRODUCTS_IN_LIST_KEY, Gson().toJson(currentProductsList))
            .apply()
    }

    override fun getProductsInList(): List<ProductInListDTOSharedPrefs> {
        val type = object : TypeToken<List<ProductInListDTOSharedPrefs>>() {}.type
        return prefs.getString(PRODUCTS_IN_LIST_KEY, null)?.let { jsonString ->
            Gson().fromJson(jsonString, type)
        } ?: emptyList()
    }

    override fun addProducts(list: List<ProductDTOSharedPrefs>) {
        prefs.edit()
            .putString(PRODUCTS_KEY, Gson().toJson((getProducts() + list).toSet())).apply()
    }

    override fun getProducts(): List<ProductDTOSharedPrefs> {
        val type = object : TypeToken<List<ProductDTOSharedPrefs>>() {}.type
        return prefs.getString(PRODUCTS_KEY, null)?.let { jsonString ->
            Gson().fromJson(jsonString, type)
        } ?: emptyList()
    }

    override fun getProductById(guid: String): ProductDTOSharedPrefs? {
        val prod = getProducts().find { productDTOSharedPrefs ->
            productDTOSharedPrefs.guid == guid
        }
        return prod
    }

    override fun incrementProductViews(guid: String) {
        val newProductInList = getProductsInList().map { productInList ->
            if (productInList.guid == guid) {
                productInList.copy(views = productInList.views + 1)
            } else {
                productInList.copy()
            }
        }
        prefs.edit()
            .putString(PRODUCTS_IN_LIST_KEY, Gson().toJson(newProductInList))
            .apply()
    }

    override fun addNewProduct(newProduct: ProductDTOSharedPrefs): Boolean {
        var currentProducts = getProducts()
        var currentProductsInList = getProductsInList()
        try {
            currentProducts = currentProducts + newProduct
            currentProductsInList =
                currentProductsInList + newProduct.toProductInListDTOSharedPrefs()
            addProducts(currentProducts)
            addProductsInList(currentProductsInList)
        } catch (e: Exception) {
            return false
        }
        return true
    }

    companion object {
        private const val SHARED_PREFERENCES_NAME = "Products DB"
        private const val PRODUCTS_IN_LIST_KEY = "Product in list"
        private const val PRODUCTS_KEY = "Products"
    }
}