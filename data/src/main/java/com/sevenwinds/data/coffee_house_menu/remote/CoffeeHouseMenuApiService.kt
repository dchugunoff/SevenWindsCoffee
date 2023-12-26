package com.sevenwinds.data.coffee_house_menu.remote

import com.sevenwinds.domain.registration.coffee_house_menu.CoffeeHouseMenu
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface CoffeeHouseMenuApiService {

    @GET("location/{id}/menu")
    @JvmSuppressWildcards
    fun getMenu(@Path("id") id: String, @Header("Authorization") token: String): Call<CoffeeHouseMenu>
}