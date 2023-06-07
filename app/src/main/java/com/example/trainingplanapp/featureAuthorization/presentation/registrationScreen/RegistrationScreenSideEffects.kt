package com.example.trainingplanapp.featureAuthorization.presentation.registrationScreen

import com.example.trainingplanapp.featureAuthorization.presentation.destinations.DirectionDestination


sealed class RegistrationScreenSideEffects {
    data class Navigate(val destination: DirectionDestination) : RegistrationScreenSideEffects()
    object NavigateBack : RegistrationScreenSideEffects()
}
