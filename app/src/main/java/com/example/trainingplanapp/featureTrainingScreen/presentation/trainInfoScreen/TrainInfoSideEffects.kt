package com.example.trainingplanapp.featureTrainingScreen.presentation.trainInfoScreen

import com.example.trainingplanapp.destinations.DirectionDestination
import com.ramcosta.composedestinations.spec.Direction

sealed class TrainInfoSideEffects {
    data class Navigate(val destination: DirectionDestination) : TrainInfoSideEffects()
    data class NavigateWithArguments(val destination: Direction) : TrainInfoSideEffects()
    data class NavigateWithResult(val destination: DirectionDestination) : TrainInfoSideEffects()
    object NavigateBack : TrainInfoSideEffects()
}
