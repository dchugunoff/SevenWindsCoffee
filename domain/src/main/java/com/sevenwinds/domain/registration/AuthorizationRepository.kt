package com.sevenwinds.domain.registration

interface AuthorizationRepository {

    suspend fun registration(email: String, password: String): Token

    suspend fun login(email: String, password: String): Token
}