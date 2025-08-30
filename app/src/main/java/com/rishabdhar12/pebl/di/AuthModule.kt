package com.rishabdhar12.pebl.di

import android.content.Context
import androidx.room.Room
import com.rishabdhar12.feature_auth.local.dao.UserDao
import com.rishabdhar12.feature_auth.repo.AuthRepository
import com.rishabdhar12.feature_auth.repo.AuthRepositoryImpl
import com.rishabdhar12.pebl.room_db_service.AppDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository (
        impl: AuthRepositoryImpl
    ): AuthRepository
}