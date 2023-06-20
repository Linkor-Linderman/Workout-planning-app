package com.example.trainingplanapp.featureExercises.presentation.exerciseInfoScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainingplanapp.R
import com.example.trainingplanapp.common.Resource
import com.example.trainingplanapp.di.utill.StringResourcesManager
import com.example.trainingplanapp.featureExercises.domain.model.DefaultValues
import com.example.trainingplanapp.featureExercises.domain.model.Exercise
import com.example.trainingplanapp.featureExercises.domain.useCase.ExerciseUseCases
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
class ExerciseInfoScreenViewModel @Inject constructor(
    private val useCases: ExerciseUseCases,
    private val stringResourcesManager: StringResourcesManager
) : ContainerHost<ExerciseInfoState, ExerciseInfoSideEffects>, ViewModel() {
    private val unexpectedErrorMessage =
        stringResourcesManager.getStringResourceById(R.string.unexpected_error)

    override val container =
        container<ExerciseInfoState, ExerciseInfoSideEffects>(
            ExerciseInfoState(
                exercise = Exercise(
                    common = true,
                    defaultValues = DefaultValues(0, 0, 0),
                    description = "",
                    imageId = "",
                    muscleGroups = emptyList(),
                    name = "",
                    published = false,
                    trainerId = ""
                )
            )
        )

    fun onEvent(event: ExerciseInfoUiEvents) {
        when (event) {
            ExerciseInfoUiEvents.DismissAlertDialog -> intent {
                reduce {
                    state.copy(errorMessage = "")
                }
            }
            ExerciseInfoUiEvents.OnBackArrowButtonClicked -> intent {
                postSideEffect(ExerciseInfoSideEffects.NavigateBack)
            }
        }
    }

    fun fetchExercise(
        exerciseId: String
    ) {
        intent {
            useCases.getExerciseByIdUseCase(
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
                                exercise = result.data!!,
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