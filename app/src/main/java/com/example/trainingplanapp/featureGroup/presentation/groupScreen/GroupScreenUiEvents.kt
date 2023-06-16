package com.example.trainingplanapp.featureGroup.presentation.groupScreen

import com.example.trainingplanapp.featureGroup.domain.model.GroupInfo

sealed class GroupScreenUiEvents{
    object DismissAlertDialog: GroupScreenUiEvents()
    object ClickToCoachButton: GroupScreenUiEvents()
    object ClickToGymGoerButton: GroupScreenUiEvents()
    object ClickToBecomeCoachButton: GroupScreenUiEvents()
    object ClickToMakeGroupButton: GroupScreenUiEvents()
    object ClickToConfirmRequestsButton: GroupScreenUiEvents()
    object ClickToAddToCoachButton: GroupScreenUiEvents()
    data class ClickToEditGroup(val groupInfo: GroupInfo): GroupScreenUiEvents()
    data class ClickToAddTrainGroup(val groupInfo: GroupInfo): GroupScreenUiEvents()
    data class ClickToDeleteGroup(val groupInfo: GroupInfo): GroupScreenUiEvents()
}
