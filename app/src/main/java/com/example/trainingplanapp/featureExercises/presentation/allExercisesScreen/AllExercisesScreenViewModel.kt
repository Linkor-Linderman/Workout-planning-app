package com.example.trainingplanapp.featureExercises.presentation.allExercisesScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainingplanapp.R
import com.example.trainingplanapp.common.Resource
import com.example.trainingplanapp.destinations.ComplexInfoScreenDestination
import com.example.trainingplanapp.destinations.ExerciseInfoScreenDestination
import com.example.trainingplanapp.di.utill.StringResourcesManager
import com.example.trainingplanapp.featureExercises.domain.model.GetComplex
import com.example.trainingplanapp.featureExercises.domain.model.GetExercise
import com.example.trainingplanapp.featureExercises.domain.model.PaginationQuery
import com.example.trainingplanapp.featureExercises.domain.model.PartOfTheBody
import com.example.trainingplanapp.featureExercises.domain.useCase.ExerciseUseCases
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
class AllExercisesScreenViewModel @Inject constructor(
    private val useCases: ExerciseUseCases,
    private val complexUseCases: ComplexUseCases,
    private val stringResourcesManager: StringResourcesManager
) : ContainerHost<AllExercisesState, AllExercisesSideEffects>, ViewModel() {
    private val unexpectedErrorMessage =
        stringResourcesManager.getStringResourceById(R.string.unexpected_error)

    override val container =
        container<AllExercisesState, AllExercisesSideEffects>(AllExercisesState())

    init {
        fetchMyExercises()
        fetchCommonExercises()
        fetchComplex()
    }

    fun onEvent(event: AllExercisesUiEvents) {
        when (event) {
            is AllExercisesUiEvents.ClickToExercise -> intent {
                postSideEffect(
                    AllExercisesSideEffects.NavigateWithArguments(
                        ExerciseInfoScreenDestination(exerciseId = event.exerciseInfo.id)
                    )
                )
            }
            AllExercisesUiEvents.DismissAlertDialog -> intent {
                reduce {
                    state.copy(errorMessage = "")
                }
            }
            AllExercisesUiEvents.OnAddExerciseButtonClick -> TODO()
            is AllExercisesUiEvents.ClickToComplex -> intent {
                postSideEffect(
                    AllExercisesSideEffects.NavigateWithArguments(
                        ComplexInfoScreenDestination(event.complex.id)
                    )
                )
            }
        }
    }

    private fun fetchMyExercises() {
        intent {
            useCases.getExerciseListUseCase(
                GetExercise(
                    common = false,
                    liked = false,
                    muscleGroups = PartOfTheBody.values().toList().map { it.name },
                    my = true,
                    name = "",
                    paginationQueryDto = PaginationQuery(1, 15),
                    shared = false,
                    published = false
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
                                listOfMyExercises = result.data!!,
                                isLoading = false,
                                errorMessage = "",
                            )
                        }
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun fetchCommonExercises() {
        intent {
            useCases.getExerciseListUseCase(
                GetExercise(
                    common = true,
                    muscleGroups = PartOfTheBody.values().toList().map { it.name },
                    my = false,
                    name = "",
                    paginationQueryDto = PaginationQuery(1, 5),
                    shared = false,
                    liked = false,
                    published = false
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
                                listOfCommonExercises = result.data!!,
                                isLoading = false,
                                errorMessage = "",
                            )
                        }
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun fetchComplex() {
        intent {
            complexUseCases.getComplexListUseCase(
                GetComplex(
                    common = false,
                    liked = false,
                    my = true,
                    name = "",
                    published = false,
                    shared = false,
                    paginationQueryDto = PaginationQuery(
                        1,
                        15
                    )
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
                                listOfSetOfExercises = result.data!!,
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