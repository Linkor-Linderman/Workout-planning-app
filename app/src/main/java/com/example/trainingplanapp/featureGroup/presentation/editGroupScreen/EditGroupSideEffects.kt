package com.example.trainingplanapp.featureGroup.presentation.editGroupScreen

import com.example.trainingplanapp.destinations.DirectionDestination

sealed class EditGroupSideEffects {
    data class Navigate(val destination: DirectionDestination) : EditGroupSideEffects()
    object NavigateBack : EditGroupSideEffects()
}
