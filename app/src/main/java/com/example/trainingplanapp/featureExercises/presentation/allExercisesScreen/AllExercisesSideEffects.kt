package com.example.trainingplanapp.featureExercises.presentation.allExercisesScreen

import com.example.trainingplanapp.destinations.DirectionDestination
import com.ramcosta.composedestinations.spec.Direction

sealed class AllExercisesSideEffects {
    data class Navigate(val destination: DirectionDestination) : AllExercisesSideEffects()
    data class NavigateWithArguments(val destination: Direction) : AllExercisesSideEffects()
    data class NavigateWithResult(val destination: DirectionDestination) : AllExercisesSideEffects()
}
