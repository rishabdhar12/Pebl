package com.rishabdhar12.feature_categories.repo

import com.rishabdhar12.services.firebase.FirebaseRemoteConfigService
import org.json.JSONObject
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val firebaseRemoteConfigService: FirebaseRemoteConfigService,
): CategoriesRepository {
    override suspend fun fetchAndActivate(): Boolean {
        return firebaseRemoteConfigService.fetchConfig()
    }

    override suspend fun getCategoriesFromRemoteConfig(): Result<JSONObject> {
        return firebaseRemoteConfigService.getJson("Categories")
    }
}
