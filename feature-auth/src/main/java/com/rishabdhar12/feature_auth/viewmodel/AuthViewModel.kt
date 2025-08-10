package com.rishabdhar12.feature_auth.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rishabdhar12.feature_auth.dto.SignUpDTO
import com.rishabdhar12.feature_auth.repo.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    var emailInput = mutableStateOf("")
        private set

    var emailErrorMessage = mutableStateOf<String?>(null)
        private set

    var passwordInput = mutableStateOf("")
        private set

    var passwordErrorMessage = mutableStateOf<String?>(null)
        private set

    var fullNameInput = mutableStateOf("")
        private set

    var fullNameErrorMessage = mutableStateOf<String?>(null)
        private set

    var mobileInput = mutableStateOf("")
        private set

    var mobileErrorMessage = mutableStateOf<String?>(null)
        private set

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _isSignedIn = MutableStateFlow<Boolean?>(null)
    val isSignedIn = _isSignedIn.asStateFlow()

    var authError = mutableStateOf<String?>(null)

    var showDialog  = MutableStateFlow(false)


    fun signInUser() {
        viewModelScope.launch {
            _isLoading.value = true
            val result = authRepository.signIn(emailInput.value, passwordInput.value)

            result.fold(
                onSuccess = {
                    authError.value = null
                    _isSignedIn.value = true
                },
                onFailure = { e ->
                    showDialog.value = true
                    authError.value = e.localizedMessage ?: "Something went wrong!"
                }
            )
            _isLoading.value = false
        }
    }

    fun signUpUser() {
        viewModelScope.launch {
            _isLoading.value = true
            val result = authRepository.signUpUser(SignUpDTO(
                email = emailInput.value,
                password = passwordInput.value,
            ))

            result.fold(
                onSuccess = { uid ->
                    authError.value = null

                    val signUpDTO = SignUpDTO(
                        uid = uid,
                        email = emailInput.value,
                        password = passwordInput.value,
                        fullName = fullNameInput.value,
                        phoneNumber = mobileInput.value
                    )

                    val jsonMap: Map<String, Any> = Gson().fromJson(
                        Gson().toJson(signUpDTO),
                        object : TypeToken<Map<String, Any>>() {}.type
                    )

                    val saveUserResult = authRepository.saveUser(jsonMap)
                    saveUserResult.fold(
                        onSuccess = { user ->
                            Log.i("", user)
                        },

                        onFailure = { e->
                            showDialog.value = true
                            authError.value = e.localizedMessage ?: "Something went wrong!"

                        }
                    )

                },
                onFailure = { e ->
                    showDialog.value = true
                    authError.value = e.localizedMessage ?: "Something went wrong!"
                }
            )
            _isLoading.value = false
        }
    }

    fun onEmailChanged(input: String) {
        emailInput.value = input
        emailErrorMessage.value = when {
            input.isBlank() -> "Email cannot be empty"
            !input.matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) -> "Invalid email format"
            else -> null
        }
    }

    fun onPasswordChanged(input: String) {
        passwordInput.value = input
        passwordErrorMessage.value = when {
            input.isBlank() -> "Password cannot be empty"
            else -> null
        }
    }

    fun onFullNameChanged(input: String) {
        fullNameInput.value = input
        fullNameErrorMessage.value = when {
            input.isBlank() -> "Please enter full name"
            !input.contains(" ") || input.split(" ").last().isBlank() -> "Both first and last name are mandatory"
            else -> null
        }
    }

    fun onPhoneNumberChanged(input: String) {
        mobileInput.value = input
        mobileErrorMessage.value = when {
            input.isBlank() -> "Phone number cannot be empty"
            input.length != 10 -> "Invalid mobile number"
            else -> null
        }
    }
}