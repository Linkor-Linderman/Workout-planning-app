package com.example.trainingplanapp.featureGroup.presentation.makeGroupScreen

import com.example.trainingplanapp.featureGroup.domain.model.UserInfo

sealed class MakeGroupScreenUiEvents {
    object DismissAlertDialog : MakeGroupScreenUiEvents()
    data class OnNameChange(val value: String) : MakeGroupScreenUiEvents()
    data class OnDescriptionChange(val value: String) : MakeGroupScreenUiEvents()
    object ClickToAddUser : MakeGroupScreenUiEvents()
    data class OnDeleteUserClick(val user: UserInfo) : MakeGroupScreenUiEvents()
    object ClickToConfirmCreating : MakeGroupScreenUiEvents()
}
