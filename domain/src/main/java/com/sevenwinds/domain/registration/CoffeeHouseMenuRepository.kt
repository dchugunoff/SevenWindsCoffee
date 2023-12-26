package com.sevenwinds.domain.registration

import com.sevenwinds.domain.registration.coffee_house_menu.CoffeeHouseMenu

interface CoffeeHouseMenuRepository {

    suspend fun getCoffeeHouseMenu(id: String, token: String): CoffeeHouseMenu
}