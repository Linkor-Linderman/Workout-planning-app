package com.example.trainingplanapp.featureAuthorization.domain.useCases

data class AuthUseCases(
    val loginUseCase: LoginUseCase,
    val registrationUseCase: RegistrationUseCase,
    val passwordValidationUseCase: PasswordValidationUseCase,
    val emailValidationUseCase: EmailValidationUseCase,
    val emptyFieldsValidationUseCase: EmptyFieldsValidationUseCase,
    val loginValidationUseCase: LoginValidationUseCase
)
