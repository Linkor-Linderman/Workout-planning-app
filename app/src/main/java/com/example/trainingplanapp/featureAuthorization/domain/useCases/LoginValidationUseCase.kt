package com.example.trainingplanapp.featureAuthorization.domain.useCases

class LoginValidationUseCase {
    operator fun invoke(login: String): Boolean{
        val loginRegex  = "^[a-z0-9_\\-]{3,16}$"
        return login.matches(loginRegex.toRegex())
    }
}