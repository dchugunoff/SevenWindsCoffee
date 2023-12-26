package com.sevenwinds.coffeeapp.presentation.coffee_house_menu_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sevenwinds.data.utils.SharedPreferencesHelper
import com.sevenwinds.domain.registration.CoffeeHouseMenuRepository
import com.sevenwinds.domain.registration.coffee_house_menu.CoffeeHouseMenu
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoffeeHouseMenuViewModel @Inject constructor(
    private val repository: CoffeeHouseMenuRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : ViewModel() {

    private val _items = MutableLiveData<CoffeeHouseMenu>()
    val items: LiveData<CoffeeHouseMenu> = _items

    fun getMenu(id: String) {
        val token = "Bearer ${sharedPreferencesHelper.getToken()}"
        viewModelScope.launch {
            try {
                _items.value = repository.getCoffeeHouseMenu(id, token)
                Log.d("COFFEE CATCH", "${_items.value}")
            } catch (e: Exception) {
                Log.d("COFFEE CATCH", "${e.message}")
            }
            Log.d("MENU", "${items.value}")
        }
    }
}