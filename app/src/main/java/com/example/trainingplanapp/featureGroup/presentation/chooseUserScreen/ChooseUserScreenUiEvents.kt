package com.example.trainingplanapp.featureGroup.presentation.chooseUserScreen

import com.example.trainingplanapp.featureGroup.domain.model.UserInfo

sealed class ChooseUserScreenUiEvents {
    object DismissAlertDialog : ChooseUserScreenUiEvents()
    data class OnSearchFieldChange(val value: String) : ChooseUserScreenUiEvents()
    data class OnChooseUser(val userInfo: UserInfo) : ChooseUserScreenUiEvents()
}

