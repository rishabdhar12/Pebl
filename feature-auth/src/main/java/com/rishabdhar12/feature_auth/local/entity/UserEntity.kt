package com.rishabdhar12.feature_auth.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val uid: String,
    val fullName: String,
    val phoneNumber: String,
    val email: String,
)