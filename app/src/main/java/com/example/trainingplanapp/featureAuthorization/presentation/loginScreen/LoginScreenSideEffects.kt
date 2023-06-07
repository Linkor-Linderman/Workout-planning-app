package com.example.trainingplanapp.featureAuthorization.presentation.loginScreen

import com.example.trainingplanapp.featureAuthorization.presentation.destinations.DirectionDestination

sealed class LoginScreenSideEffects {
    data class Navigate(val destination: DirectionDestination) : LoginScreenSideEffects()
}
