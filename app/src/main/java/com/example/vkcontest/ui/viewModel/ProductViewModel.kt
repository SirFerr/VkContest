package com.example.vkcontest.ui.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkcontest.data.Retrofit
import com.example.vkcontest.data.model.Products
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ProductViewModel() : ViewModel() {
    var products = MutableStateFlow(Products(1, listOf(), 1, 1))
    var listOfCategories = MutableStateFlow(value = listOf(""))
    private var skip = mutableStateOf(0)
    private var limit = mutableStateOf(20)
    private var category = mutableStateOf("1")

    init {
        getAllCategories()
        fetchProducts()

    }

    private fun fetchProducts() {
        Log.d("1",category.value)
        viewModelScope.launch {

            try {
                 products.value =
                    if (category.value !in listOfCategories.value) {
                    Retrofit.api.fetchProducts(limit.value, skip.value)
                } else {
                    Retrofit.api.fetchProductsByCategory(
                        category.value,limit.value, skip.value
                    )
                }

                Log.d("debug", products.value.toString())
            } catch (e: Exception) {

            }
        }
    }

    private fun getAllCategories() {
        viewModelScope.launch {
            try {
                val _listOfCategories = Retrofit.api.getAllCategories()
                if (_listOfCategories != null) {
                    listOfCategories.value = _listOfCategories
                }
                Log.d("debug", products.value.toString())
            } catch (e: Exception) {
            }
        }
    }

    fun nextPage() {
        if (skip.value + limit.value < products.value.total) {
            skip.value += limit.value
        } else if (skip.value + limit.value >= products.value.total && products.value.total % limit.value != 0) {
            skip.value += products.value.total % limit.value
        }
        fetchProducts()
    }

    fun prevPage() {
        if (skip.value >= limit.value) {
            skip.value -= limit.value
        } else if (skip.value < limit.value && skip.value > 0) {
            skip.value = 0
        }
        fetchProducts()
    }

    fun selectCategory(_category: String) {
        category.value = _category
        skip.value=0
        Log.d("category", category.value)
        fetchProducts()
    }
}

