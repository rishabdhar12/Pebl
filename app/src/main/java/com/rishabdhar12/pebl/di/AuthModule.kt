package com.rishabdhar12.pebl.di

import com.rishabdhar12.feature_auth.repo.AuthRepository
import com.rishabdhar12.feature_auth.repo.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {

    @Binds
    @Singleton
    abstract  fun bindAuthRepository (
        impl: AuthRepositoryImpl
    ): AuthRepository
}