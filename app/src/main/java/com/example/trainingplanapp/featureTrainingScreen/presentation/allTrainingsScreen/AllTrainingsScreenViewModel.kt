package com.example.trainingplanapp.featureTrainingScreen.presentation.allTrainingsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainingplanapp.R
import com.example.trainingplanapp.common.Resource
import com.example.trainingplanapp.destinations.PlayTrainScreenDestination
import com.example.trainingplanapp.destinations.TrainInfoScreenDestination
import com.example.trainingplanapp.di.utill.StringResourcesManager
import com.example.trainingplanapp.featureTrainingScreen.domain.model.PaginationQuery
import com.example.trainingplanapp.featureTrainingScreen.domain.model.QueryTraining
import com.example.trainingplanapp.featureTrainingScreen.domain.useCase.TrainingUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class AllTrainingsScreenViewModel @Inject constructor(
    private val useCases: TrainingUseCases,
    private val stringResourcesManager: StringResourcesManager
) : ContainerHost<AllTrainingsState, AllTrainingsSideEffects>, ViewModel() {
    private val unexpectedErrorMessage =
        stringResourcesManager.getStringResourceById(R.string.unexpected_error)

    override val container =
        container<AllTrainingsState, AllTrainingsSideEffects>(AllTrainingsState())

    init {
        fetchMyTrains()
        fetchLikedTrainings()
        fetchAppointedTrains()
    }

    fun onEvent(event: AllTrainingsUiEvents) {
        when (event) {
            is AllTrainingsUiEvents.ClickToAppointed -> intent {
                postSideEffect(
                    AllTrainingsSideEffects.NavigateWithArguments(
                        TrainInfoScreenDestination(
                            trainId = event.appointedTraining.id,
                            true
                        )
                    )
                )
            }
            is AllTrainingsUiEvents.ClickToTraining -> intent {
                postSideEffect(
                    AllTrainingsSideEffects.NavigateWithArguments(
                        TrainInfoScreenDestination(
                            trainId = event.trainingInfo.id,
                            false
                        )
                    )
                )
            }
            AllTrainingsUiEvents.DismissAlertDialog -> intent {
                reduce {
                    state.copy(
                        errorMessage = ""
                    )
                }
            }
            is AllTrainingsUiEvents.ClickToTrainingPlay -> intent {
                postSideEffect(
                    AllTrainingsSideEffects.NavigateWithArguments(
                        PlayTrainScreenDestination(
                            event.trainingInfo.id,
                            false
                        )
                    )
                )
            }
            is AllTrainingsUiEvents.ClickToAppointedTrainingPlay -> intent {
                postSideEffect(
                    AllTrainingsSideEffects.NavigateWithArguments(
                        PlayTrainScreenDestination(
                            event.trainingInfo.id,
                            true
                        )
                    )
                )
            }
        }
    }

    private fun fetchMyTrains() {
        intent {
            useCases.getTrainingListUseCase(
                QueryTraining(
                    common = false,
                    liked = false,
                    my = true,
                    name = "",
                    paginationQueryDto = PaginationQuery(
                        1,
                        15
                    ),
                    published = false,
                    shared = false
                )
            ).onEach { result ->
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
                                listOfMyTraining = result.data!!,
                                isLoading = false,
                                errorMessage = "",
                            )
                        }
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun fetchAppointedTrains() {
        intent {
            useCases.getAppointedTrainingsUseCase().onEach { result ->
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
                                listOfAppointedTraining = result.data!!,
                                isLoading = false,
                                errorMessage = "",
                            )
                        }
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun fetchLikedTrainings() {
        intent {
            useCases.getTrainingListUseCase(
                QueryTraining(
                    common = false,
                    liked = false,
                    my = false,
                    name = "",
                    paginationQueryDto = PaginationQuery(
                        1,
                        15
                    ),
                    published = false,
                    shared = false
                )
            ).onEach { result ->
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
                                listOfLikedTraining = result.data!!,
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