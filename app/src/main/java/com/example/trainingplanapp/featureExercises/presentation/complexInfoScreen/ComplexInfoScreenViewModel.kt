package com.example.trainingplanapp.featureExercises.presentation.complexInfoScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainingplanapp.R
import com.example.trainingplanapp.common.Resource
import com.example.trainingplanapp.destinations.ExerciseInfoScreenDestination
import com.example.trainingplanapp.di.utill.StringResourcesManager
import com.example.trainingplanapp.featureExercises.domain.model.Complex
import com.example.trainingplanapp.featureExercises.domain.useCase.complex.ComplexUseCases
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
class ComplexInfoScreenViewModel @Inject constructor(
    private val useCases: ComplexUseCases,
    private val stringResourcesManager: StringResourcesManager
) : ContainerHost<ComplexInfoState, ComplexInfoSideEffects>, ViewModel() {
    private val unexpectedErrorMessage =
        stringResourcesManager.getStringResourceById(R.string.unexpected_error)

    override val container =
        container<ComplexInfoState, ComplexInfoSideEffects>(
            ComplexInfoState(
                complex = Complex(
                    common = true,
                    complexType = "MANY",
                    description = "",
                    name = "",
                    published = false,
                    spaceDuration = 0,
                    template = false,
                    exercises = emptyList(),
                    exercisesInformation = emptyList(),
                    id = "",
                    repetitions = 0
                )
            )
        )

    fun onEvent(event: ComplexInfoUiEvents) {
        when (event) {
            ComplexInfoUiEvents.DismissAlertDialog -> intent {
                reduce {
                    state.copy(errorMessage = "")
                }
            }
            ComplexInfoUiEvents.OnBackArrowButtonClicked -> intent {
                postSideEffect(ComplexInfoSideEffects.NavigateBack)
            }
            is ComplexInfoUiEvents.OnExerciseClick -> intent {
                postSideEffect(
                    ComplexInfoSideEffects.NavigateWithArguments(
                        ExerciseInfoScreenDestination(event.exerciseInfo.id)
                    )
                )
            }
        }
    }

    fun fetchExercise(
        exerciseId: String
    ) {
        intent {
            useCases.getComplexByIdUseCase(
                exerciseId
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
                                complex = result.data!!,
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