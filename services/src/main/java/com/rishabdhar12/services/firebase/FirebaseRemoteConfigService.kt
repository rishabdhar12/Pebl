package com.rishabdhar12.services.firebase

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import kotlinx.coroutines.tasks.await
import org.json.JSONException
import org.json.JSONObject
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

    @RequiresApi(Build.VERSION_CODES.O_MR1)
    fun getJson(key: String): Result<JSONObject> {
        return try {
            firebaseRemoteConfig.fetchAndActivate()
            val jsonString = firebaseRemoteConfig.getString(key)
            if (jsonString.isBlank()) {
                Result.failure(IllegalArgumentException("Remote Config value for key '$key' is empty or blank, cannot parse as JSON."))
            } else {
                val jsonObject = JSONObject(jsonString)
                Result.success(jsonObject)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
            Result.failure(JSONException("Failed to parse JSON for key '$key': ${e.message}", e))
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}