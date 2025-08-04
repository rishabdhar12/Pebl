package com.rishabdhar12.feature_auth.repo

import com.rishabdhar12.services.firebase.FirebaseAuthService
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private  val firebaseAuthService: FirebaseAuthService
): AuthRepository{

    override suspend fun signIn(email: String, password: String): Result<Unit> {
        return firebaseAuthService.signInWithEmail(email, password)
    }

}