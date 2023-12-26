package com.sevenwinds.data.coffee_shops.repository

import com.sevenwinds.data.coffee_shops.remote.CoffeeShopsRetrofitClient
import com.sevenwinds.data.utils.SharedPreferencesHelper
import com.sevenwinds.domain.registration.CoffeeShopsRepository
import com.sevenwinds.domain.registration.coffee_shops.CoffeeShop
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume

import kotlin.coroutines.suspendCoroutine

class CoffeeShopsRepositoryImpl(
    private val coffeeShopsClient: CoffeeShopsRetrofitClient
) : CoffeeShopsRepository {
    override suspend fun getCoffeeShopsList(token: String): CoffeeShop {
        return suspendCoroutine { continuation ->
            val call = coffeeShopsClient.instance.getCoffeeShops(token)
            call.enqueue(object : Callback<CoffeeShop> {
                override fun onResponse(call: Call<CoffeeShop>, response: Response<CoffeeShop>) {
                    if (response.isSuccessful) {
                        val responseData = response.body()
                        continuation.resume(responseData ?: CoffeeShop())
                    } else {
                        continuation.resume(CoffeeShop())
                    }
                }

                override fun onFailure(call: Call<CoffeeShop>, t: Throwable) {
                    continuation.resume(CoffeeShop())
                }

            })
        }
    }
}