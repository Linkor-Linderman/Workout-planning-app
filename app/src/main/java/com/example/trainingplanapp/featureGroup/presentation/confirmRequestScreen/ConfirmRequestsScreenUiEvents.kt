package com.example.trainingplanapp.featureGroup.presentation.confirmRequestScreen

import com.example.trainingplanapp.featureGroup.domain.model.RequestInfo

sealed class ConfirmRequestsScreenUiEvents {
    object DismissAlertDialog : ConfirmRequestsScreenUiEvents()
    data class OnSearchFieldChange(val value: String) : ConfirmRequestsScreenUiEvents()
    data class OnAcceptRequest(val requestInfo: RequestInfo) : ConfirmRequestsScreenUiEvents()
    data class OnDismissRequest(val requestInfo: RequestInfo) : ConfirmRequestsScreenUiEvents()
}
