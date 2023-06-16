package com.example.trainingplanapp.featureGroup.presentation.editGroupScreen

import com.example.trainingplanapp.featureGroup.domain.model.UserInfo

sealed class EditGroupScreenUiEvents {
    object DismissAlertDialog : EditGroupScreenUiEvents()
    data class OnNameChange(val value: String) : EditGroupScreenUiEvents()
    data class OnDescriptionChange(val value: String) : EditGroupScreenUiEvents()
    object ClickToAddUser : EditGroupScreenUiEvents()
    data class OnDeleteUserClick(val user: UserInfo) : EditGroupScreenUiEvents()
    object ClickToConfirmEditing : EditGroupScreenUiEvents()
}
