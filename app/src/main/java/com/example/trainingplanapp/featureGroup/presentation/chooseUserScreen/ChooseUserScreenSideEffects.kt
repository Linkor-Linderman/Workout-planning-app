package com.example.trainingplanapp.featureGroup.presentation.chooseUserScreen

import com.example.trainingplanapp.featureGroup.domain.model.UserInfo

sealed class ChooseUserScreenSideEffects {
    data class NavigateBack(val result: UserInfo) : ChooseUserScreenSideEffects()
}
