package com.example.trainingplanapp.featureGroup.presentation.groupScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainingplanapp.R
import com.example.trainingplanapp.common.Resource
import com.example.trainingplanapp.destinations.ConfirmRequestsScreenDestination
import com.example.trainingplanapp.destinations.EditGroupScreenDestination
import com.example.trainingplanapp.destinations.MakeGroupScreenDestination
import com.example.trainingplanapp.destinations.SearchCoachScreenDestination
import com.example.trainingplanapp.di.utill.StringResourcesManager
import com.example.trainingplanapp.featureGroup.domain.useCase.CoachUseCases
import com.example.trainingplanapp.featureGroup.domain.useCase.GroupUseCases
import com.example.trainingplanapp.featureMainScreen.domain.useCases.ProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.util.*
import javax.inject.Inject

@HiltViewModel
class GroupScreenViewModel @Inject constructor(
    private val groupUseCase: GroupUseCases,
    private val profileUseCase: ProfileUseCase,
    private val coachUseCase: CoachUseCases,
    private val stringResourcesManager: StringResourcesManager
) : ContainerHost<GroupScreenState, GroupScreenSideEffect>, ViewModel() {
    private val unexpectedErrorMessage =
        stringResourcesManager.getStringResourceById(R.string.unexpected_error)

    override val container =
        container<GroupScreenState, GroupScreenSideEffect>(GroupScreenState())

    fun onEvent(event: GroupScreenUiEvents) {
        when (event) {
            GroupScreenUiEvents.ClickToAddToCoachButton -> intent {
                postSideEffect(GroupScreenSideEffect.Navigate(SearchCoachScreenDestination))
            }
            GroupScreenUiEvents.ClickToBecomeCoachButton -> becomeCoach()
            GroupScreenUiEvents.ClickToCoachButton -> intent {
                reduce {
                    state.copy(isFirstButtonChosen = true)
                }
            }
            GroupScreenUiEvents.ClickToGymGoerButton -> intent {
                reduce {
                    state.copy(isFirstButtonChosen = false)
                }
            }
            GroupScreenUiEvents.ClickToMakeGroupButton -> intent {
                postSideEffect(GroupScreenSideEffect.Navigate(MakeGroupScreenDestination))
            }
            GroupScreenUiEvents.DismissAlertDialog -> intent {
                reduce {
                    state.copy(errorMessage = "")
                }
            }
            GroupScreenUiEvents.ClickToConfirmRequestsButton -> intent {
                postSideEffect(GroupScreenSideEffect.Navigate(ConfirmRequestsScreenDestination))
            }
            is GroupScreenUiEvents.ClickToAddTrainGroup -> TODO()
            is GroupScreenUiEvents.ClickToDeleteGroup -> deleteCoachGroup(event.groupInfo.id)
            is GroupScreenUiEvents.ClickToEditGroup -> intent {
                postSideEffect(GroupScreenSideEffect.NavigateWithArguments(EditGroupScreenDestination(event.groupInfo.id)))
            }
        }
    }

    private fun becomeCoach() {
        intent {
            coachUseCase.becomeCoachUseCase(state.name.lowercase(Locale.getDefault()))
                .onEach { result ->
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
                                    isCoach = true,
                                    isLoading = false,
                                    errorMessage = "",
                                )
                            }
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }

    fun fetchMyGroup() {
        intent {
            groupUseCase.getMyGroupsUseCase("").onEach { result ->
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
                                yourGroups = result.data!!,
                                isLoading = false,
                                errorMessage = "",
                            )
                        }
                    }
                }
            }.launchIn(viewModelScope)

        }
    }

    private fun fetchTrainingGroup() {
        intent {
            if (state.isCoach) {
                groupUseCase.getTrainingGroupUseCase("").onEach { result ->
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
                                    subordinateGroups = result.data!!,
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

    fun checkIsUserCoach() {
        intent {
            profileUseCase.getProfileUseCase().onEach { result ->
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
                                isCoach = result.data!!.isTrainer,
                                name = result.data.name,
                                isLoading = false,
                                errorMessage = "",
                            )
                        }
                        fetchTrainingGroup()
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun deleteCoachGroup(groupId: String) {
        intent {
            groupUseCase.deleteGroupByIdUseCase(groupId).onEach { result ->
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
                                subordinateGroups = state.subordinateGroups.filter { it.id != groupId },
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