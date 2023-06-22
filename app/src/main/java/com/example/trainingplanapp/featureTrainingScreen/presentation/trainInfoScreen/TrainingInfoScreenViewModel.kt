package com.example.trainingplanapp.featureTrainingScreen.presentation.trainInfoScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainingplanapp.R
import com.example.trainingplanapp.common.Resource
import com.example.trainingplanapp.destinations.PlayTrainScreenDestination
import com.example.trainingplanapp.di.utill.StringResourcesManager
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
class TrainingInfoScreenViewModel @Inject constructor(
    private val useCases: TrainingUseCases,
    private val stringResourcesManager: StringResourcesManager
) : ContainerHost<TrainInfoState, TrainInfoSideEffects>, ViewModel() {
    private val unexpectedErrorMessage =
        stringResourcesManager.getStringResourceById(R.string.unexpected_error)

    override val container =
        container<TrainInfoState, TrainInfoSideEffects>(TrainInfoState())


    fun onEvent(event: TrainInfoUiEvents) {
        when (event) {
            TrainInfoUiEvents.DismissAlertDialog -> intent {
                reduce {
                    state.copy(
                        errorMessage = ""
                    )
                }
            }
            TrainInfoUiEvents.ClickToNavigateBack -> intent {
                postSideEffect(TrainInfoSideEffects.NavigateBack)
            }
            TrainInfoUiEvents.ClickToPlay -> intent {
                postSideEffect(
                    TrainInfoSideEffects.NavigateWithArguments(
                        PlayTrainScreenDestination(
                            state.training!!.id,
                            state.isAppointed
                        )
                    )
                )
            }
        }
    }

    fun fetchTrainById(
        trainId: String,
        isAppointed: Boolean
    ) {
        intent {
            if (isAppointed) {
                useCases.getAppointedTrainingByIdUseCase(trainId).onEach { result ->
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
                                    training = result.data!!,
                                    isAppointed = true,
                                    listOfTrainingListItem = useCases.getSortedByOrderTrainingItemInListUseCase(
                                        result.data
                                    ),
                                    isLoading = false,
                                    errorMessage = "",
                                )
                            }
                        }
                    }
                }.launchIn(viewModelScope)
            } else {
                useCases.getTrainingByIdUseCase(trainId).onEach { result ->
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
                                    training = result.data!!,
                                    isAppointed = false,
                                    listOfTrainingListItem = useCases.getSortedByOrderTrainingItemInListUseCase(
                                        result.data
                                    ),
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