package com.rishabdhar12.services.firebase

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseFirestoreService @Inject constructor(
    private val fireStore: FirebaseFirestore
) {
    suspend fun addUser(json: Map<String, Any>): Result<String> {
        return try {
            val docRef = fireStore.collection("users").document()
            docRef.set(json).await()
            Result.success(docRef.id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}