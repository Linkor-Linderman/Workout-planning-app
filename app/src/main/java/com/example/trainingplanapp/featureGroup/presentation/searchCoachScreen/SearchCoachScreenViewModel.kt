package com.example.trainingplanapp.featureGroup.presentation.searchCoachScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainingplanapp.R
import com.example.trainingplanapp.common.Resource
import com.example.trainingplanapp.di.utill.StringResourcesManager
import com.example.trainingplanapp.featureGroup.domain.model.CoachInfo
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
class SearchCoachScreenViewModel @Inject constructor(
    private val coachUseCase: CoachUseCases,
    private val stringResourcesManager: StringResourcesManager
) : ContainerHost<SearchCoachScreenState, SearchCoachScreenSideEffects>, ViewModel() {
    private val unexpectedErrorMessage =
        stringResourcesManager.getStringResourceById(R.string.unexpected_error)

    override val container =
        container<SearchCoachScreenState, SearchCoachScreenSideEffects>(SearchCoachScreenState())

    init {
        fetchInitCoachList()
    }

    fun onEvent(event: SearchCoachUiEvents) {
        when (event) {
            SearchCoachUiEvents.CancelChoose -> intent {
                reduce {
                    state.copy(isConfirmDialogIsVisible = false)
                }
            }
            SearchCoachUiEvents.ConfirmChoose -> intent {
                reduce {
                    state.copy(isConfirmDialogIsVisible = false)
                }
            }
            SearchCoachUiEvents.DismissAlertDialog -> intent {
                reduce {
                    state.copy(errorMessage = "")
                }
            }
            is SearchCoachUiEvents.OnCoachSelect -> {
                addToCoach(event.coachInfo)
            }
            is SearchCoachUiEvents.OnSearchFieldChange -> onSearch(event.value)
        }
    }

    private fun fetchInitCoachList() {
        intent {
            coachUseCase.getCoachListUseCase("").onEach { result ->
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
                                coachList = result.data!!,
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
            reduce { state.copy(shortName = name) }
            searchJob?.cancel()
            searchJob = viewModelScope.launch {
                delay(500L)
                coachUseCase.getCoachListUseCase(name).onEach { result ->
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
                                    coachList = result.data!!,
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

    private fun addToCoach(coachInfo: CoachInfo) {
        intent {
            coachUseCase.addToCoachUseCase(coachInfo.id).onEach { result ->
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
                                isConfirmDialogIsVisible = true,
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