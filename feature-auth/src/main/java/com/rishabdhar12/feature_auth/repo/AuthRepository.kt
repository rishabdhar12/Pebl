package com.rishabdhar12.feature_auth.repo

import com.rishabdhar12.feature_auth.dto.SignUpDTO

interface AuthRepository {
    suspend fun signIn(email: String, password: String): Result<Unit>
    suspend fun signUpUser(signUpDTO: SignUpDTO): Result<String>
    suspend fun saveUser(json: Map<String, Any>): Result<String>
}