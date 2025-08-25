package com.rishabdhar12.feature_auth.repo

import com.rishabdhar12.feature_auth.dto.SignUpDTO
import com.rishabdhar12.feature_auth.local.entity.UserEntity

interface AuthRepository {
    suspend fun signIn(email: String, password: String): Result<Unit>
    suspend fun signUpUser(signUpDTO: SignUpDTO): Result<String>
    suspend fun saveUser(json: Map<String, Any>): Result<String>
    suspend fun insertUser(user: UserEntity)
}