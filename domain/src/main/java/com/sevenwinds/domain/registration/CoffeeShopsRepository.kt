package com.sevenwinds.domain.registration

import com.sevenwinds.domain.registration.coffee_shops.CoffeeShop

interface CoffeeShopsRepository {

    suspend fun getCoffeeShopsList(token: String): CoffeeShop
}