package com.rishabdhar12.feature_categories.repo

import org.json.JSONObject

interface CategoriesRepository {
    suspend fun fetchAndActivate(): Boolean
    suspend fun getCategoriesFromRemoteConfig(): Result<JSONObject>
}