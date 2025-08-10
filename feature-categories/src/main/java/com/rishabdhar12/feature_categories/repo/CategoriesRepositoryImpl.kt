package com.rishabdhar12.feature_categories.repo

import com.rishabdhar12.services.firebase.FirebaseRemoteConfigService
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val firebaseRemoteConfigService: FirebaseRemoteConfigService,
): CategoriesRepository {
    override suspend fun getCategoriesFromRemoteConfig(): Result<String> {
        return firebaseRemoteConfigService.getString("Categories")
    }
}
