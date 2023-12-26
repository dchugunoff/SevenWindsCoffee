package com.sevenwinds.data.coffee_shops.remote

import com.sevenwinds.domain.registration.coffee_shops.CoffeeShop
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface CoffeeShopsApiService {

    @GET("locations")
    @JvmSuppressWildcards
    fun getCoffeeShops(@Header("Authorization") token: String): Call<CoffeeShop>
}