package com.rishabdhar12.feature_categories.repo

import com.rishabdhar12.feature_categories.local.entity.SelectedCategoryEntity
import com.rishabdhar12.feature_categories.models.SelectedCategoriesModel
import kotlinx.coroutines.flow.Flow
import org.json.JSONObject

interface CategoriesRepository {
    suspend fun fetchAndActivate(): Boolean
    suspend fun getCategoriesFromRemoteConfig(): Result<JSONObject>

    suspend fun insertCategories(categories: List<SelectedCategoryEntity>)
    suspend fun getAllCategories(): Result<Flow<List<SelectedCategoriesModel>>>
}