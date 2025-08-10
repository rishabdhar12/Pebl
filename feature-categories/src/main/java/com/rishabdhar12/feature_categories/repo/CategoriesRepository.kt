package com.rishabdhar12.feature_categories.repo

interface CategoriesRepository {
    suspend fun getCategoriesFromRemoteConfig(): Result<String>
}