package com.rishabdhar12.pebl.di

import com.rishabdhar12.feature_categories.repo.CategoriesRepository
import com.rishabdhar12.feature_categories.repo.CategoriesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CategoriesModule {

    @Binds
    @Singleton
    abstract  fun bindCategoriesRepository (
        impl: CategoriesRepositoryImpl
    ): CategoriesRepository
}
