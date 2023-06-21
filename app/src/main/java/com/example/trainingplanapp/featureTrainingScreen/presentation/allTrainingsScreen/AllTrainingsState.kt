package com.example.trainingplanapp.featureTrainingScreen.presentation.allTrainingsScreen

import com.example.trainingplanapp.featureTrainingScreen.domain.model.AppointedTraining
import com.example.trainingplanapp.featureTrainingScreen.domain.model.TrainingInfo

data class AllTrainingsState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val listOfMyTraining: List<TrainingInfo> = emptyList(),
    val listOfLikedTraining: List<TrainingInfo> = emptyList(),
    val listOfAppointedTraining: List<AppointedTraining> = emptyList(),
)
