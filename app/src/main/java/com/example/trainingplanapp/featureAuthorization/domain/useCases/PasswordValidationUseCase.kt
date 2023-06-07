package com.example.trainingplanapp.featureAuthorization.domain.useCases

class PasswordValidationUseCase {
    operator fun invoke(password: String): Boolean{
        val passwordRegex  = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$"
        return password.matches(passwordRegex.toRegex())
    }
}