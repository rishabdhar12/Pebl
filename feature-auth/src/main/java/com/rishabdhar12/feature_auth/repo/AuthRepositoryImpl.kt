package com.rishabdhar12.feature_auth.repo

import com.rishabdhar12.feature_auth.dto.SignUpDTO
import com.rishabdhar12.feature_auth.local.dao.UserDao
import com.rishabdhar12.feature_auth.local.entity.UserEntity
import com.rishabdhar12.services.firebase.FirebaseAuthService
import com.rishabdhar12.services.firebase.FirebaseFirestoreService
import com.rishabdhar12.services.firebase.FirebaseRemoteConfigService
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuthService: FirebaseAuthService,
    private val firebaseFirestoreService: FirebaseFirestoreService,
    private val userDao: UserDao,
): AuthRepository{

    override suspend fun signIn(email: String, password: String): Result<String> {
        return firebaseAuthService.signInWithEmail(email, password)
    }

    override suspend fun signUpUser(signUpDTO: SignUpDTO): Result<String> {
        return firebaseAuthService.signUpWithEmail(
            email = signUpDTO.email!!,
            password = signUpDTO.password!!,
        )
    }

    override suspend fun saveUser(json: Map<String, Any>): Result<String> {
        return firebaseFirestoreService.addUser(json)
    }


    override suspend fun insertUser(user: UserEntity) = userDao.insertUser(user)

    override suspend fun getUser(): Result<UserEntity> {
        return try {
            val user = userDao.getUser()
            if (user != null) {
                Result.success(user)
            } else {
                Result.failure(Exception("No user found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getUserFirebase(userId: String):Result<List<Map<String, Any>>> {
        return firebaseFirestoreService.getUser(userId)
    }
}