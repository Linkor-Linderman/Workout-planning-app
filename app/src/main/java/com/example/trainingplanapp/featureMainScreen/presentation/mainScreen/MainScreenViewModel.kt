package com.example.trainingplanapp.featureMainScreen.presentation.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainingplanapp.R
import com.example.trainingplanapp.common.Resource
import com.example.trainingplanapp.di.utill.StringResourcesManager
import com.example.trainingplanapp.featureMainScreen.domain.useCases.ProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val useCase: ProfileUseCase,
    private val stringResourcesManager: StringResourcesManager
) : ContainerHost<MainScreenState, MainScreenSideEffects>, ViewModel() {

    private val unexpectedErrorMessage =
        stringResourcesManager.getStringResourceById(R.string.unexpected_error)

    override val container =
        container<MainScreenState, MainScreenSideEffects>(MainScreenState())

    init {
        fetchCurrentDate()
        fetchUserName()
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
        }
    }

    fun fetchCurrentDate() {
        intent {
            reduce {
                state.copy(currentDate = useCase.getCurrentDateUseCase())
            }
        }
    }

    fun fetchUserName() {
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
}