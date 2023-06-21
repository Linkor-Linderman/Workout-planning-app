package com.example.trainingplanapp.featureTrainingScreen.presentation.allTrainingsScreen

import com.example.trainingplanapp.featureTrainingScreen.domain.model.AppointedTraining
import com.example.trainingplanapp.featureTrainingScreen.domain.model.TrainingInfo

sealed class AllTrainingsUiEvents {
    object DismissAlertDialog : AllTrainingsUiEvents()
    data class ClickToTraining(val trainingInfo: TrainingInfo) : AllTrainingsUiEvents()
    data class ClickToTrainingPlay(val trainingInfo: TrainingInfo) : AllTrainingsUiEvents()
    data class ClickToAppointed(val appointedTraining: AppointedTraining) : AllTrainingsUiEvents()
}