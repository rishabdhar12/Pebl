package com.rishabdhar12.pebl.di

import android.content.Context
import androidx.room.Room
import com.rishabdhar12.feature_auth.local.dao.UserDao
import com.rishabdhar12.feature_categories.local.dao.CategoriesDao
import com.rishabdhar12.pebl.room_db_service.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProvidesRoomModule {


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "pebl_database"
        )
            .fallbackToDestructiveMigration(true)
            .build()
    }

    @Provides
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()

    @Provides
    fun provideCategoriesDao(db: AppDatabase): CategoriesDao = db.categoriesDao()
}