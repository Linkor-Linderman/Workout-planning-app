package com.example.trainingplanapp.featureGroup.presentation.makeGroupScreen

import com.example.trainingplanapp.destinations.DirectionDestination

sealed class MakeGroupSideEffects {
    data class Navigate(val destination: DirectionDestination) : MakeGroupSideEffects()
    object NavigateBack : MakeGroupSideEffects()
}
