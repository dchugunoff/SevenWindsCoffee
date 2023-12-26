package com.sevenwinds.data.coffee_shops.remote

import com.sevenwinds.data.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CoffeeShopsRetrofitClient {
    val instance: CoffeeShopsApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(CoffeeShopsApiService::class.java)
    }
}