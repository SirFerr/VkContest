package com.example.vkcontest.data.model

data class Product(
    val brand: String = "test",
    val category: String = "test",
    val description: String = "test",
    val discountPercentage: Double = 1.0,
    val id: Int = 1,
    val images: List<String> = listOf(),
    val price: Int = 1,
    val rating: Double = 1.0,
    val stock: Int = 1,
    val thumbnail: String = "test",
    val title: String = "test"
)