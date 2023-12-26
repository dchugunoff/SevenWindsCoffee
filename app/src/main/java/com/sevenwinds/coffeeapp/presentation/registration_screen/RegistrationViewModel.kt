package com.sevenwinds.coffeeapp.presentation.registration_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sevenwinds.coffeeapp.presentation.utils.ErrorResponse
import com.sevenwinds.coffeeapp.presentation.utils.ResultState
import com.sevenwinds.coffeeapp.presentation.utils.SuccessResponse
import com.sevenwinds.domain.registration.AuthorizationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(private val repository: AuthorizationRepository) :
    ViewModel() {

    private val _dataState = MutableLiveData<ResultState>()
    val dataState: LiveData<ResultState> = _dataState

    fun registrationUser(email: String, password: String) {
        viewModelScope.launch {
            try {
                val token = repository.registration(email, password).token
                if (token != null) {
                    _dataState.value = SuccessResponse
                } else {
                    _dataState.value = ErrorResponse
                }
            } catch (e: Exception) {
                _dataState.value = ErrorResponse
            }
        }
    }
}