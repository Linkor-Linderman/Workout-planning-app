package com.example.trainingplanapp.featureMainScreen.presentation.mainScreen

import com.example.trainingplanapp.destinations.DirectionDestination

sealed class MainScreenSideEffects {
    data class Navigate(val destination: DirectionDestination) : MainScreenSideEffects()
    object NavigateBack : MainScreenSideEffects()
}

