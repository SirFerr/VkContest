package com.example.vkcontest.data.api

import com.example.vkcontest.data.model.Product
import com.example.vkcontest.data.model.Products
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {
    @GET("products")
    suspend fun fetchProducts(
        @Query("limit") limit: Int,
        @Query("skip") skip: Int,
        @Query("select") select: String = "title,description,thumbnail,id"
    ): Products

    @GET("products/{id}")
    suspend fun getById(
        @Path("id") id: Int,
    ): Product

    @GET("products/categories")
    suspend fun getAllCategories(): List<String>


    @GET("products/category/{category}")
    suspend fun fetchProductsByCategory(
        @Path("category") category: String,
        @Query("limit") limit: Int,
        @Query("skip") skip: Int,
        @Query("select") select: String = "title,description,thumbnail,id"
    ): Products

    @GET("products/search")
    suspend fun searchProduct(
        @Query("q") text: String
    ): Products
}