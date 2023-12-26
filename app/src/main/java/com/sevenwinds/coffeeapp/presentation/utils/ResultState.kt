package com.sevenwinds.coffeeapp.presentation.utils

sealed interface ResultState
object SuccessResponse : ResultState
object ErrorResponse : ResultState