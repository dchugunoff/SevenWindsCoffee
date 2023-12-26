package com.sevenwinds.data.di

import com.sevenwinds.data.registration.remote.AuthorizationRetrofitClient
import com.sevenwinds.data.registration.repository.AuthorizationRepositoryImpl
import com.sevenwinds.domain.registration.AuthorizationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideRegistrationRepository(): AuthorizationRepository {
        return AuthorizationRepositoryImpl(AuthorizationRetrofitClient)
    }
}