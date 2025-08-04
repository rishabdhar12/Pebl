package com.rishabdhar12.feature_auth.repo

interface AuthRepository {
    suspend fun signIn(email: String, password: String): Result<Unit>
}