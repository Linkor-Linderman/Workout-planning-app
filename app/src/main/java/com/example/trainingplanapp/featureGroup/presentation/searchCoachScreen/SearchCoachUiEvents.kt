package com.example.trainingplanapp.featureGroup.presentation.searchCoachScreen

import com.example.trainingplanapp.featureGroup.domain.model.CoachInfo

sealed class SearchCoachUiEvents {
    object DismissAlertDialog : SearchCoachUiEvents()
    data class OnSearchFieldChange(val value: String): SearchCoachUiEvents()
    data class OnCoachSelect(val coachInfo: CoachInfo): SearchCoachUiEvents()
    object ConfirmChoose : SearchCoachUiEvents()
    object CancelChoose : SearchCoachUiEvents()
}
