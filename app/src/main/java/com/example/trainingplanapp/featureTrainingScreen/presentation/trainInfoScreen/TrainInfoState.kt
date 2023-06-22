package com.example.trainingplanapp.featureTrainingScreen.presentation.trainInfoScreen

import com.example.trainingplanapp.featureTrainingScreen.domain.model.trainingInfo.TrainingListItem
import com.example.trainingplanapp.featureTrainingScreen.domain.model.trainingInfo.TrainingWithExtendedComplexInfo

data class TrainInfoState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val training: TrainingWithExtendedComplexInfo? = null,
    val isAppointed: Boolean = false,
    val listOfTrainingListItem: List<TrainingListItem> = emptyList()
)
