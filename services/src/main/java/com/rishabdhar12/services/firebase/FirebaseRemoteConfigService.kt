package com.rishabdhar12.services.firebase

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseRemoteConfigService @Inject constructor(
    private val firebaseRemoteConfig: FirebaseRemoteConfig
) {
    init {
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(3600)
            .build()
        firebaseRemoteConfig.setConfigSettingsAsync(configSettings)
    }

    suspend fun fetchConfig(): Boolean {
        return try {
            firebaseRemoteConfig.fetchAndActivate().await()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun getString(key: String): Result<String> {
        return try {
            val result = firebaseRemoteConfig.getString(key)
            Result.success(result)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

}