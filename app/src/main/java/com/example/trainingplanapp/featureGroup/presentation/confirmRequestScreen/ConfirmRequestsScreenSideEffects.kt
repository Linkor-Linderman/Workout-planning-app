package com.example.trainingplanapp.featureGroup.presentation.confirmRequestScreen

import com.example.trainingplanapp.destinations.DirectionDestination

sealed class ConfirmRequestsScreenSideEffects {
    data class Navigate(val destination: DirectionDestination) : ConfirmRequestsScreenSideEffects()
    object NavigateBack : ConfirmRequestsScreenSideEffects()
}

