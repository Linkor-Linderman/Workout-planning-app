package com.example.trainingplanapp.featureTrainingScreen.presentation.playTrainScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainingplanapp.R
import com.example.trainingplanapp.common.Resource
import com.example.trainingplanapp.di.utill.StringResourcesManager
import com.example.trainingplanapp.featureTrainingScreen.domain.model.trainingInfo.ComplexInTrainExtended
import com.example.trainingplanapp.featureTrainingScreen.domain.model.trainingInfo.ExerciseInTrain
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
class PlayTrainScreenViewModel @Inject constructor(
    private val useCases: TrainingUseCases,
    private val stringResourcesManager: StringResourcesManager
) : ContainerHost<PlayTrainInfoState, PlayTrainSideEffects>, ViewModel() {
    private val unexpectedErrorMessage =
        stringResourcesManager.getStringResourceById(R.string.unexpected_error)

    override val container =
        container<PlayTrainInfoState, PlayTrainSideEffects>(PlayTrainInfoState())


    fun onEvent(event: PlayTrainInfoUiEvents) {
        when (event) {
            PlayTrainInfoUiEvents.DismissAlertDialog -> intent {
                reduce {
                    state.copy(
                        errorMessage = ""
                    )
                }
            }
            PlayTrainInfoUiEvents.ClickToNavigateBack -> intent {
                postSideEffect(PlayTrainSideEffects.NavigateBack)
            }
            PlayTrainInfoUiEvents.ClickToCompleteAll -> intent {
                reduce {
                    state.copy(listOfTrainingListItem = emptyList())
                }
            }
            is PlayTrainInfoUiEvents.OnComplexCompleteButtonClick -> intent {
                reduce {
                    state.copy(listOfTrainingListItem = state.listOfTrainingListItem.filter {
                        when (it) {
                            is ComplexInTrainExtended -> it.complexId != event.complexInTrain.complexId
                            else -> {
                                true
                            }
                        }
                    })
                }
            }
            is PlayTrainInfoUiEvents.OnExerciseCompleteButtonClick -> intent {
                reduce {
                    state.copy(listOfTrainingListItem = state.listOfTrainingListItem.filter {
                        when (it) {
                            is ExerciseInTrain ->
                                it.exerciseId != event.exercise.exerciseId
                                        && it.orderNumber != event.exercise.orderNumber
                            else -> {
                                true
                            }
                        }
                    })
                }
            }
            is PlayTrainInfoUiEvents.OnExerciseCompleteInComplexButtonClick -> intent {
                val newComplex = deleteExerciseInComplex(
                    event.complexInTrain,
                    event.exerciseInTrain
                )
                Log.d("SOME", newComplex.toString())
                if (newComplex!!.exerciseValues.isEmpty()) {
                    reduce {
                        state.copy(listOfTrainingListItem = state.listOfTrainingListItem.filter {
                            when (it) {
                                is ComplexInTrainExtended -> it.complexId != event.complexInTrain.complexId
                                else -> {
                                    true
                                }
                            }
                        })
                    }
                } else {
                    reduce {
                        val newList = state.listOfTrainingListItem.toMutableList()
                        newList.replaceAll {
                            if (it is ComplexInTrainExtended && it.complexId == newComplex.complexId) {
                                newComplex
                            } else {
                                it
                            }
                        }
                        state.copy(
                            listOfTrainingListItem = newList
                        )
                    }
                }
            }
        }
    }

    private fun deleteExerciseInComplex(
        complexInTrainExtended: ComplexInTrainExtended,
        exerciseInTrain: ExerciseInTrain
    ): ComplexInTrainExtended? {
        var result: ComplexInTrainExtended? = null
        val listOfExercise = complexInTrainExtended.exerciseValues.filterNot {
            it.exerciseId == exerciseInTrain.exerciseId
        }
        result = ComplexInTrainExtended(
            complexInTrainExtended.complexId,
            complexInTrainExtended.name,
            complexInTrainExtended.description,
            listOfExercise,
            complexInTrainExtended.orderNumber
        )
        return result
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