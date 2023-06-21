package com.example.trainingplanapp.featureTrainingScreen.presentation.allTrainingsScreen

import com.example.trainingplanapp.destinations.DirectionDestination
import com.ramcosta.composedestinations.spec.Direction

sealed class AllTrainingsSideEffects {
    data class Navigate(val destination: DirectionDestination) : AllTrainingsSideEffects()
    data class NavigateWithArguments(val destination: Direction) : AllTrainingsSideEffects()
    data class NavigateWithResult(val destination: DirectionDestination) : AllTrainingsSideEffects()
}
