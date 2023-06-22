package com.example.trainingplanapp.featureMainScreen.presentation.mainScreen

import com.example.trainingplanapp.featureTrainingScreen.domain.model.AppointedTraining

sealed class MainScreenUiEvents {
    object DismissAlertDialog : MainScreenUiEvents()
    object ClickToSeeAll : MainScreenUiEvents()
    data class ClickToTraining(val id: String): MainScreenUiEvents()
    data class ClickToAppointedTrainingPlay(val trainingInfo: AppointedTraining) :
        MainScreenUiEvents()
    data class ClickToAppointed(val appointedTraining: AppointedTraining) : MainScreenUiEvents()
}