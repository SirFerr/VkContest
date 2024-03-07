package com.example.vkcontest.data.model

import kotlinx.coroutines.flow.MutableStateFlow

data class Products(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)