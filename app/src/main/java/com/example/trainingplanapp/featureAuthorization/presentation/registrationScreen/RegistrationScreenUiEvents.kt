package com.example.trainingplanapp.featureAuthorization.presentation.registrationScreen

sealed class RegistrationScreenUiEvents {
    data class ChangeEmailValue(val value: String): RegistrationScreenUiEvents()
    data class ChangePasswordValue(val value: String): RegistrationScreenUiEvents()
    data class ChangeNameValue(val value: String): RegistrationScreenUiEvents()
    data class ChangeLoginValue(val value: String): RegistrationScreenUiEvents()
    data class ChangeRepeatPasswordValue(val value: String): RegistrationScreenUiEvents()
    object ClickToSigninButton: RegistrationScreenUiEvents()
    object ClickToSignupButton: RegistrationScreenUiEvents()
    object DismissAlertDialog: RegistrationScreenUiEvents()
    object DismissConfirmDialog: RegistrationScreenUiEvents()
}
