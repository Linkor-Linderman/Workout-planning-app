package com.example.trainingplanapp.featureTrainingScreen.presentation.playTrainScreen

import com.example.trainingplanapp.featureTrainingScreen.domain.model.trainingInfo.TrainingListItem
import com.example.trainingplanapp.featureTrainingScreen.domain.model.trainingInfo.TrainingWithExtendedComplexInfo

data class PlayTrainInfoState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val training: TrainingWithExtendedComplexInfo? = null,
    val listOfTrainingListItem: List<TrainingListItem> = emptyList()
)
