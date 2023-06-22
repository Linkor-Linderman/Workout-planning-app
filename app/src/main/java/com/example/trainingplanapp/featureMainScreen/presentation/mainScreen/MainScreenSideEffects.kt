package com.example.trainingplanapp.featureMainScreen.presentation.mainScreen

import com.example.trainingplanapp.destinations.DirectionDestination
import com.ramcosta.composedestinations.spec.Direction

sealed class MainScreenSideEffects {
    data class Navigate(val destination: DirectionDestination) : MainScreenSideEffects()
    object NavigateBack : MainScreenSideEffects()
    data class NavigateWithArguments(val destination: Direction) : MainScreenSideEffects()
}

