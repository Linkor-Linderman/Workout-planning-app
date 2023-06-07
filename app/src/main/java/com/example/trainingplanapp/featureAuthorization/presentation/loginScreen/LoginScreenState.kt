package com.example.trainingplanapp.featureAuthorization.presentation.loginScreen

data class LoginScreenState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val email: String = "",
    val password: String = "",
)
