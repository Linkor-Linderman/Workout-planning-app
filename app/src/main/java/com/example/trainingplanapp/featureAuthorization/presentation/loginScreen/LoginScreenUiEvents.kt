package com.example.trainingplanapp.featureAuthorization.presentation.loginScreen

sealed class LoginScreenUiEvents {
    data class ChangeEmailValue(val value: String): LoginScreenUiEvents()
    data class ChangePasswordValue(val value: String): LoginScreenUiEvents()
    object ClickToLoginButton: LoginScreenUiEvents()
    object ClickToSigninButton: LoginScreenUiEvents()
    object DismissAlertDialog: LoginScreenUiEvents()
}
