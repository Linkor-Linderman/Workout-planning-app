package com.example.trainingplanapp.featureGroup.presentation.searchCoachScreen

import com.example.trainingplanapp.destinations.DirectionDestination

sealed class SearchCoachScreenSideEffects {
    data class Navigate(val destination: DirectionDestination) : SearchCoachScreenSideEffects()
    object NavigateBack : SearchCoachScreenSideEffects()
}

