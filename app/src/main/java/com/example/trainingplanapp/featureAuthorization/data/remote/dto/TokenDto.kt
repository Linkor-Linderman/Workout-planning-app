package com.example.trainingplanapp.featureAuthorization.data.remote.dto

data class TokenDto(
    val accessToken: String,
    val restoreToken: String
)