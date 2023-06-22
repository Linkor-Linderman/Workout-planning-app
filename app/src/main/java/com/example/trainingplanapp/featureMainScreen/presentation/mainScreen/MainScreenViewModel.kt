package com.example.trainingplanapp.featureMainScreen.presentation.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainingplanapp.R
import com.example.trainingplanapp.common.Resource
import com.example.trainingplanapp.destinations.PlayTrainScreenDestination
import com.example.trainingplanapp.destinations.TrainInfoScreenDestination
import com.example.trainingplanapp.di.utill.StringResourcesManager
import com.example.trainingplanapp.featureMainScreen.domain.useCases.ProfileUseCase
import com.example.trainingplanapp.featureTrainingScreen.domain.useCase.TrainingUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val useCase: ProfileUseCase,
    private val trainingUseCases: TrainingUseCases,
    private val stringResourcesManager: StringResourcesManager
) : ContainerHost<MainScreenState, MainScreenSideEffects>, ViewModel() {

    private val unexpectedErrorMessage =
        stringResourcesManager.getStringResourceById(R.string.unexpected_error)

    override val container =
        container<MainScreenState, MainScreenSideEffects>(MainScreenState())

    init {
        fetchCurrentDate()
        fetchUserName()
        fetchAppointedTrains()
    }

    fun onEvent(event: MainScreenUiEvents) {
        when (event) {
            MainScreenUiEvents.ClickToSeeAll -> TODO()
            is MainScreenUiEvents.ClickToTraining -> TODO()
            MainScreenUiEvents.DismissAlertDialog -> intent {
                reduce {
                    state.copy(errorMessage = "")
                }
            }
            is MainScreenUiEvents.ClickToAppointed -> intent {
                postSideEffect(
                    MainScreenSideEffects.NavigateWithArguments(
                        TrainInfoScreenDestination(
                            event.appointedTraining.id,
                            true
                        )
                    )
                )
            }
            is MainScreenUiEvents.ClickToAppointedTrainingPlay -> intent {
                postSideEffect(
                    MainScreenSideEffects.NavigateWithArguments(
                        PlayTrainScreenDestination(
                            event.trainingInfo.id,
                            true
                        )
                    )
                )
            }
        }
    }

    private fun fetchCurrentDate() {
        intent {
            reduce {
                state.copy(currentDate = useCase.getCurrentDateUseCase())
            }
        }
    }

    private fun fetchUserName() {
        intent {
            useCase.getProfileUseCase().onEach { result ->
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
                                name = result.data?.name ?: "User",
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
            trainingUseCases.getAppointedTrainingsUseCase().onEach { result ->
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
                            val today = LocalDate.now()
                            val filteredTrainings = result.data!!.filter { training ->
                                training.dates.any { date ->
                                    val localDate =
                                        Instant.ofEpochMilli(date).atZone(ZoneId.systemDefault())
                                            .toLocalDate()
                                    localDate == today
                                }
                            }
                            state.copy(
                                currentDayTrainings = filteredTrainings,
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
