package com.example.trainingplanapp.featureAuthorization.presentation.registrationScreen

data class RegistrationScreenState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = "",
    val name: String = "",
    val login: String = "",
    val applicationSubmitted: Boolean = false
)
