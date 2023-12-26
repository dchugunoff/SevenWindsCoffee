package com.sevenwinds.data.registration.remote

import com.sevenwinds.data.registration.models.LoginData
import com.sevenwinds.domain.registration.Token
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthorizationApiService {
    @POST("auth/register")
    @JvmSuppressWildcards
    fun registration(@Body loginData: LoginData): Call<Token>

    @POST("auth/login")
    @JvmSuppressWildcards
    fun authorization(@Body loginData: LoginData): Call<Token>

}