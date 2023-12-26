package com.sevenwinds.data.coffee_house_menu.repository

import com.sevenwinds.data.coffee_house_menu.remote.CoffeeHouseMenuRetrofitClient
import com.sevenwinds.domain.registration.CoffeeHouseMenuRepository
import com.sevenwinds.domain.registration.coffee_house_menu.CoffeeHouseMenu
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class CoffeeHouseMenuRepositoryImpl @Inject constructor(
    private val retrofitClient: CoffeeHouseMenuRetrofitClient
) : CoffeeHouseMenuRepository {
    override suspend fun getCoffeeHouseMenu(id: String, token: String): CoffeeHouseMenu {
        return suspendCoroutine { continuation ->
            val call = retrofitClient.instance.getMenu(id, token)
            call.enqueue(object : Callback<CoffeeHouseMenu> {
                override fun onResponse(
                    call: Call<CoffeeHouseMenu>,
                    response: Response<CoffeeHouseMenu>
                ) {
                    if (response.isSuccessful) {
                        val responseData = response.body()
                        continuation.resume(responseData ?: CoffeeHouseMenu())
                    } else {
                        continuation.resume(CoffeeHouseMenu())
                    }
                }

                override fun onFailure(call: Call<CoffeeHouseMenu>, t: Throwable) {
                    continuation.resume(CoffeeHouseMenu())
                }

            })
        }
    }
}