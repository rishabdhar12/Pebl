package com.rishabdhar12.feature_auth.dto

data class SignUpDTO(
    val uid: String? = null,
    val fullName: String? = null,
    val phoneNumber: String? = null,
    val email: String? = null,
    val password: String? = null,
)
