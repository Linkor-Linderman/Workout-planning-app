package com.example.trainingplanapp.featureGroup.presentation.searchCoachScreen

import com.example.trainingplanapp.featureGroup.domain.model.CoachInfo

data class SearchCoachScreenState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val shortName: String = "",
    val chosenCoach: CoachInfo? = null,
    val coachList: List<CoachInfo> = emptyList(),
    val isConfirmDialogIsVisible: Boolean = false
)
