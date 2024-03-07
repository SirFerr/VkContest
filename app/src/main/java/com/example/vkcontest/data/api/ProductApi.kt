package com.example.vkcontest.data.api

import android.telecom.Call
import com.example.vkcontest.data.model.Product
import com.example.vkcontest.data.model.Products
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApi {
    @GET("products")
    fun fetchProducts(@Query("limit") limit: Int, @Query("skip") skip: Int, @Query("select") select: String = "title,price,description,thumbnail"): Products

}