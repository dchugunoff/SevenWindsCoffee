package com.sevenwinds.domain.registration

data class Token(
    val token: String?,
    val tokenLifetime: Int?
)