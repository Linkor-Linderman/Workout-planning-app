package com.example.trainingplanapp.featureAuthorization.domain.useCases

class EmailValidationUseCase {
    operator fun invoke(email: String): Boolean{
        val emailRegex  = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$"
        return email.matches(emailRegex.toRegex())
    }
}