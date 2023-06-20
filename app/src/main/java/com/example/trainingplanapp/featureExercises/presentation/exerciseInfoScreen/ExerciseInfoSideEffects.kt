package com.example.trainingplanapp.featureExercises.presentation.exerciseInfoScreen

import com.example.trainingplanapp.destinations.DirectionDestination
import com.ramcosta.composedestinations.spec.Direction

sealed class ExerciseInfoSideEffects {
    data class Navigate(val destination: DirectionDestination) : ExerciseInfoSideEffects()
    data class NavigateWithArguments(val destination: Direction) : ExerciseInfoSideEffects()
    data class NavigateWithResult(val destination: DirectionDestination) : ExerciseInfoSideEffects()
    object NavigateBack: ExerciseInfoSideEffects()
}
