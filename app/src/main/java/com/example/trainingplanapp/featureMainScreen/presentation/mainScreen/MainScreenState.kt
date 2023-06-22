package com.example.trainingplanapp.featureMainScreen.presentation.mainScreen

import com.example.trainingplanapp.featureTrainingScreen.domain.model.AppointedTraining

data class MainScreenState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val name: String = "",
    val currentDate: String = "",
    val currentDayTrainings: List<AppointedTraining> = emptyList()
)