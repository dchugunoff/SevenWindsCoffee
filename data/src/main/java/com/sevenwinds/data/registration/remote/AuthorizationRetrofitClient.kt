package com.sevenwinds.data.registration.remote

import com.sevenwinds.data.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



object AuthorizationRetrofitClient {
    val instance: AuthorizationApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(AuthorizationApiService::class.java)
    }
}