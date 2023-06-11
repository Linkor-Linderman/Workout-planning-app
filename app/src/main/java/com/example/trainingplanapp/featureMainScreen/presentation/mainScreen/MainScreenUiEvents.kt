package com.example.trainingplanapp.featureMainScreen.presentation.mainScreen

sealed class MainScreenUiEvents {
    object DismissAlertDialog : MainScreenUiEvents()
    object ClickToSeeAll : MainScreenUiEvents()
    data class ClickToTraining(val id: String): MainScreenUiEvents()
}