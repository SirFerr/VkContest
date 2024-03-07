package com.example.vkcontest.data

import com.example.vkcontest.data.api.ProductApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
    private const val BASE_URL = "https://dummyjson.com/"
    val api: ProductApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ProductApi::class.java)
    }
}