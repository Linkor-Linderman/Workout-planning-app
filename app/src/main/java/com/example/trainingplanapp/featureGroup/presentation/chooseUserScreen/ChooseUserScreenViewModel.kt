package com.example.trainingplanapp.featureGroup.presentation.chooseUserScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainingplanapp.R
import com.example.trainingplanapp.common.Resource
import com.example.trainingplanapp.di.utill.StringResourcesManager
import com.example.trainingplanapp.featureGroup.domain.useCase.CoachUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ChooseUserScreenViewModel @Inject constructor(
    private val coachUseCase: CoachUseCases,
    private val stringResourcesManager: StringResourcesManager
) : ContainerHost<ChooseUserScreenState, ChooseUserScreenSideEffects>, ViewModel() {
    private val unexpectedErrorMessage =
        stringResourcesManager.getStringResourceById(R.string.unexpected_error)

    override val container =
        container<ChooseUserScreenState, ChooseUserScreenSideEffects>(
            ChooseUserScreenState()
        )

    init {
        fetchInitUserList()
    }

    fun onEvent(event: ChooseUserScreenUiEvents) {
        when (event) {
            ChooseUserScreenUiEvents.DismissAlertDialog -> intent {
                reduce {
                    state.copy(errorMessage = "")
                }
            }
            is ChooseUserScreenUiEvents.OnChooseUser -> intent {
                postSideEffect(ChooseUserScreenSideEffects.NavigateBack(event.userInfo))
            }
            is ChooseUserScreenUiEvents.OnSearchFieldChange -> onSearch(event.value)
        }
    }

    private fun fetchInitUserList() {
        intent {
            coachUseCase.getUsersUseCase("").onEach { result ->
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
                                userList = result.data!!,
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
                coachUseCase.getUsersUseCase(name).onEach { result ->
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
                                    userList = result.data!!,
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
}