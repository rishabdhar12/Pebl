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

    suspend fun getUser(userId: String): Result<List<Map<String, Any>>> {
        return try {
            val snapshot = fireStore.collection("users")
//                .document(userId)
                .whereEqualTo("uid", userId)
                .get()
                .await()

            if (!snapshot.isEmpty) {
                val data = snapshot.documents.mapNotNull { it.data }
                Result.success(data)
            } else {
                Result.failure(Exception("User not found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}