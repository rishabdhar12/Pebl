package com.rishabdhar12.feature_categories.repo

import com.rishabdhar12.feature_categories.local.dao.CategoriesDao
import com.rishabdhar12.feature_categories.local.entity.SelectedCategoryEntity
import com.rishabdhar12.feature_categories.models.SelectedCategoriesModel
import com.rishabdhar12.feature_categories.models.toModel
import com.rishabdhar12.services.firebase.FirebaseRemoteConfigService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.json.JSONObject
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val firebaseRemoteConfigService: FirebaseRemoteConfigService,
    private val categoriesDao: CategoriesDao,
): CategoriesRepository {
    override suspend fun fetchAndActivate(): Boolean {
        return firebaseRemoteConfigService.fetchConfig()
    }

    override suspend fun getCategoriesFromRemoteConfig(): Result<JSONObject> {
        return firebaseRemoteConfigService.getJson("Categories")
    }

    override suspend fun insertCategories(categories: List<SelectedCategoryEntity>) {
        return categoriesDao.insertCategories(categories = categories)
    }

    override suspend fun getAllCategories(): Result<Flow<List<SelectedCategoriesModel>>> {
        return try {
            val flow: Flow<List<SelectedCategoriesModel>> =
                categoriesDao.getAllCategories().map { entities ->
                    entities.map { it.toModel() }
                }

            Result.success(flow)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }}
