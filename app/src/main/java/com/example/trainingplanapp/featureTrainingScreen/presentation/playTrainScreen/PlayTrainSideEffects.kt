package com.example.trainingplanapp.featureTrainingScreen.presentation.playTrainScreen

import com.example.trainingplanapp.destinations.DirectionDestination
import com.ramcosta.composedestinations.spec.Direction

sealed class PlayTrainSideEffects {
    data class Navigate(val destination: DirectionDestination) : PlayTrainSideEffects()
    data class NavigateWithArguments(val destination: Direction) : PlayTrainSideEffects()
    data class NavigateWithResult(val destination: DirectionDestination) : PlayTrainSideEffects()
    object NavigateBack : PlayTrainSideEffects()
}
