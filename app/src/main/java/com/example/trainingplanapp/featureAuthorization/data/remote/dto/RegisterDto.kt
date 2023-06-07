package com.example.trainingplanapp.featureAuthorization.data.remote.dto

data class RegisterDto(
    val email: String,
    val login: String,
    val name: String,
    val password: String
)