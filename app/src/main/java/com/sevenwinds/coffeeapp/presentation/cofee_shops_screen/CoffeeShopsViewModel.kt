package com.sevenwinds.coffeeapp.presentation.cofee_shops_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sevenwinds.data.utils.SharedPreferencesHelper
import com.sevenwinds.domain.registration.CoffeeShopsRepository
import com.sevenwinds.domain.registration.coffee_shops.CoffeeShop
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoffeeShopsViewModel @Inject constructor(
    private val repository: CoffeeShopsRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : ViewModel() {

    private val _coffeeShopsList = MutableLiveData<CoffeeShop>()
    val coffeeShopsList: LiveData<CoffeeShop> = _coffeeShopsList

    init {
        getCoffeeShopsList()
    }

    fun getCoffeeShopsList() {
        viewModelScope.launch {
            val token = "Bearer ${sharedPreferencesHelper.getToken()}"
            try {
                _coffeeShopsList.value = repository.getCoffeeShopsList(token)
                Log.d("COFFEE CATCH", "${_coffeeShopsList.value}")
            } catch (e: Exception) {
                Log.d("COFFEE CATCH", "${e.message}")
            }
        }
    }
}