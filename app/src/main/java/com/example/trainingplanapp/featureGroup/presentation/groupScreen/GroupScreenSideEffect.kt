package com.example.trainingplanapp.featureGroup.presentation.groupScreen

import com.example.trainingplanapp.destinations.DirectionDestination
import com.ramcosta.composedestinations.spec.Direction

sealed class GroupScreenSideEffect {
    data class Navigate(val destination: DirectionDestination) : GroupScreenSideEffect()
    data class NavigateWithArguments(val destination: Direction) : GroupScreenSideEffect()
    data class NavigateWithResult(val destination: DirectionDestination) : GroupScreenSideEffect()
}
