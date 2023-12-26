package com.sevenwinds.data.di

import android.content.Context
import com.sevenwinds.data.coffee_shops.remote.CoffeeShopsRetrofitClient
import com.sevenwinds.data.coffee_shops.repository.CoffeeShopsRepositoryImpl
import com.sevenwinds.data.registration.remote.AuthorizationRetrofitClient
import com.sevenwinds.data.registration.repository.AuthorizationRepositoryImpl
import com.sevenwinds.data.utils.SharedPreferencesHelper
import com.sevenwinds.domain.registration.AuthorizationRepository
import com.sevenwinds.domain.registration.CoffeeShopsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Singleton
    @Provides
    fun provideSharedPreferencesHelper(@ApplicationContext appContext: Context): SharedPreferencesHelper {
        return SharedPreferencesHelper(appContext)
    }

    @Singleton
    @Provides
    fun provideCoffeeShopsRepository(): CoffeeShopsRepository {
        return CoffeeShopsRepositoryImpl(CoffeeShopsRetrofitClient)
    }
}