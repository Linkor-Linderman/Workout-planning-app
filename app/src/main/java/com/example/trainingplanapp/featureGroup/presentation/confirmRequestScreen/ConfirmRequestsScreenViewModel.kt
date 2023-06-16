package com.example.trainingplanapp.featureGroup.presentation.confirmRequestScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainingplanapp.R
import com.example.trainingplanapp.common.Resource
import com.example.trainingplanapp.di.utill.StringResourcesManager
import com.example.trainingplanapp.featureGroup.domain.model.RequestInfo
import com.example.trainingplanapp.featureGroup.domain.useCase.CoachUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ConfirmRequestsScreenViewModel @Inject constructor(
    private val coachUseCase: CoachUseCases,
    private val stringResourcesManager: StringResourcesManager
) : ContainerHost<ConfirmRequestsScreenState, ConfirmRequestsScreenSideEffects>, ViewModel() {
    private val unexpectedErrorMessage =
        stringResourcesManager.getStringResourceById(R.string.unexpected_error)

    override val container =
        container<ConfirmRequestsScreenState, ConfirmRequestsScreenSideEffects>(
            ConfirmRequestsScreenState()
        )

    init {
        fetchIniRequestList()
    }

    fun onEvent(event: ConfirmRequestsScreenUiEvents) {
        when (event) {
            is ConfirmRequestsScreenUiEvents.OnSearchFieldChange -> onSearch(event.value)
            ConfirmRequestsScreenUiEvents.DismissAlertDialog -> intent {
                reduce {
                    state.copy(errorMessage = "")
                }
            }
            is ConfirmRequestsScreenUiEvents.OnAcceptRequest -> acceptRequest(event.requestInfo)
            is ConfirmRequestsScreenUiEvents.OnDismissRequest -> dismissRequest(event.requestInfo)
        }
    }

    private fun fetchIniRequestList() {
        intent {
            coachUseCase.requestsUseCase("").onEach { result ->
                when (result) {
                    is Resource.Error -> reduce {
                        state.copy(
                            isLoading = false,
                            errorMessage = result.message ?: unexpectedErrorMessage
                        )
                    }
                    is Resource.Loading -> reduce {
                        state.copy(
                            isLoading = true,
                            errorMessage = ""
                        )
                    }
                    is Resource.Success -> {
                        reduce {
                            state.copy(
                                requestList = result.data!!,
                                isLoading = false,
                                errorMessage = "",
                            )
                        }
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private var searchJob: Job? = null

    private fun onSearch(name: String) {
        intent {
            reduce { state.copy(name = name) }
            searchJob?.cancel()
            searchJob = viewModelScope.launch {
                delay(500L)
                coachUseCase.requestsUseCase(name).onEach { result ->
                    when (result) {
                        is Resource.Error -> reduce {
                            state.copy(
                                isLoading = false,
                                errorMessage = result.message ?: unexpectedErrorMessage
                            )
                        }
                        is Resource.Loading -> reduce {
                            state.copy(
                                isLoading = true,
                                errorMessage = ""
                            )
                        }
                        is Resource.Success -> {
                            reduce {
                                state.copy(
                                    requestList = result.data!!,
                                    isLoading = false,
                                    errorMessage = "",
                                )
                            }
                        }
                    }
                }.launchIn(viewModelScope)
            }
        }
    }

    private fun acceptRequest(request: RequestInfo) {
        intent {
            coachUseCase.acceptRequestUseCase(request.queryId).onEach { result ->
                when (result) {
                    is Resource.Error -> reduce {
                        state.copy(
                            isLoading = false,
                            errorMessage = result.message ?: unexpectedErrorMessage
                        )
                    }
                    is Resource.Loading -> reduce {
                        state.copy(
                            isLoading = true,
                            errorMessage = ""
                        )
                    }
                    is Resource.Success -> {
                        reduce {
                            state.copy(
                                requestList = state.requestList.filter { it.queryId != request.queryId },
                                isLoading = false,
                                errorMessage = "",
                            )
                        }
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
    private fun dismissRequest(request: RequestInfo) {
        intent {
            coachUseCase.dismissRequestUseCase(request.queryId).onEach { result ->
                when (result) {
                    is Resource.Error -> reduce {
                        state.copy(
                            isLoading = false,
                            errorMessage = result.message ?: unexpectedErrorMessage
                        )
                    }
                    is Resource.Loading -> reduce {
                        state.copy(
                            isLoading = true,
                            errorMessage = ""
                        )
                    }
                    is Resource.Success -> {
                        reduce {
                            state.copy(
                                requestList = state.requestList.filter { it.queryId != request.queryId },
                                isLoading = false,
                                errorMessage = "",
                            )
                        }
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}