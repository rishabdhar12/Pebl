package com.rishabdhar12.feature_auth.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private val _authState = MutableStateFlow<Result<Unit>?>(null)
    val authState = _authState.asStateFlow()

    fun signUpUser() {
        viewModelScope.launch {
            authRepository.signIn(emailInput.value, passwordInput.value)
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
            !input.contains(" ") && input.split(" ").last().isBlank() -> "Both first and last name are mandatory"
            else -> null
        }
    }

    fun onPhoneNumberChanged(input: String) {
        mobileInput.value = input
        mobileErrorMessage.value = when {
            input.isBlank() -> "Phone number cannot be empty"
            input.length < 10 -> "Invalid mobile number"
            else -> null
        }
    }
}