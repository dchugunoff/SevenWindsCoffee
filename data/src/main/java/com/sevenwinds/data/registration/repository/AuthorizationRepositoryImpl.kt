package com.sevenwinds.data.registration.repository

import com.sevenwinds.data.registration.models.LoginData
import com.sevenwinds.data.registration.remote.AuthorizationRetrofitClient
import com.sevenwinds.domain.registration.AuthorizationRepository
import com.sevenwinds.domain.registration.Token
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AuthorizationRepositoryImpl(
    private val registrationClient: AuthorizationRetrofitClient
) : AuthorizationRepository {
    override suspend fun registration(email: String, password: String): Token {
        val requestModel = LoginData(email, password)
        return suspendCoroutine { continuation ->
            val call = registrationClient.instance.registration(loginData = requestModel)
            call.enqueue(object : Callback<Token> {
                override fun onResponse(call: Call<Token>, response: Response<Token>) {
                    if (response.isSuccessful) {
                        val responseData = response.body()
                        continuation.resume(responseData ?: Token(null, null))
                    } else {
                        continuation.resume(Token(null, null))
                    }
                }

                override fun onFailure(call: Call<Token>, t: Throwable) {
                    continuation.resume(Token(null, null))
                }
            })
        }
    }

    override suspend fun login(email: String, password: String): Token {
        val requestModel = LoginData(email, password)
        return suspendCoroutine { continuation ->
            val call = registrationClient.instance.authorization(loginData = requestModel)
            call.enqueue(object : Callback<Token> {
                override fun onResponse(call: Call<Token>, response: Response<Token>) {
                    if (response.isSuccessful) {
                        val responseData = response.body()
                        continuation.resume(responseData ?: Token(null, null))
                    } else {
                        continuation.resume(Token(null, null))
                    }
                }

                override fun onFailure(call: Call<Token>, t: Throwable) {
                    continuation.resume(Token(null, null))
                }
            })
        }
    }


}