package com.example.trainingplanapp.featureExercises.presentation.complexInfoScreen

import com.example.trainingplanapp.destinations.DirectionDestination
import com.ramcosta.composedestinations.spec.Direction

sealed class ComplexInfoSideEffects {
    data class Navigate(val destination: DirectionDestination) : ComplexInfoSideEffects()
    data class NavigateWithArguments(val destination: Direction) : ComplexInfoSideEffects()
    data class NavigateWithResult(val destination: DirectionDestination) : ComplexInfoSideEffects()
    object NavigateBack: ComplexInfoSideEffects()
}
