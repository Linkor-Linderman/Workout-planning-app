package com.example.trainingplanapp.featureGroup.presentation.makeGroupScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainingplanapp.R
import com.example.trainingplanapp.common.Resource
import com.example.trainingplanapp.destinations.SearchUserScreenDestination
import com.example.trainingplanapp.di.utill.StringResourcesManager
import com.example.trainingplanapp.featureAuthorization.domain.useCases.AuthUseCases
import com.example.trainingplanapp.featureGroup.domain.model.GroupCreate
import com.example.trainingplanapp.featureGroup.domain.model.UserInfo
import com.example.trainingplanapp.featureGroup.domain.useCase.GroupUseCases
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
class MakeGroupScreenViewModel @Inject constructor(
    private val groupUseCase: GroupUseCases,
    private val authUseCase: AuthUseCases,
    private val stringResourcesManager: StringResourcesManager
) : ContainerHost<MakeGroupScreenState, MakeGroupSideEffects>, ViewModel() {
    private val unexpectedErrorMessage =
        stringResourcesManager.getStringResourceById(R.string.unexpected_error)

    override val container =
        container<MakeGroupScreenState, MakeGroupSideEffects>(MakeGroupScreenState())

    fun onEvent(event: MakeGroupScreenUiEvents) {
        when (event) {
            MakeGroupScreenUiEvents.ClickToAddUser -> intent {
                postSideEffect(MakeGroupSideEffects.Navigate(SearchUserScreenDestination))
            }
            MakeGroupScreenUiEvents.ClickToConfirmCreating -> createGroup()
            MakeGroupScreenUiEvents.DismissAlertDialog -> intent {
                reduce {
                    state.copy(errorMessage = "")
                }
            }
            is MakeGroupScreenUiEvents.OnDeleteUserClick -> intent {
                reduce {
                    state.copy(userInfoList = state.userInfoList.filter { it.id != event.user.id })
                }
            }
            is MakeGroupScreenUiEvents.OnDescriptionChange -> intent {
                reduce {
                    state.copy(description = event.value)
                }
            }
            is MakeGroupScreenUiEvents.OnNameChange -> intent {
                reduce {
                    state.copy(name = event.value)
                }
            }
        }
    }

    fun addUserInList(userInfo: UserInfo) {
        intent {
            reduce {
                val newList = state.userInfoList.toMutableList()
                newList.add(userInfo)
                state.copy(
                    userInfoList = newList.toSet().toList()
                )
            }
        }
    }

    private fun createGroup() {
        intent {
            if (!authUseCase.emptyFieldsValidationUseCase(state.name, state.description)) {
                reduce { state.copy(errorMessage = stringResourcesManager.getStringResourceById(R.string.empty_validation)) }
            } else {
                groupUseCase.createGroupUseCase(
                    GroupCreate(
                        description = state.description,
                        name = state.name,
                        trainers = emptyList(),
                        users = state.userInfoList.map { it.id }
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
                                    isLoading = false,
                                    errorMessage = "",
                                )
                            }
                            postSideEffect(MakeGroupSideEffects.NavigateBack)
                        }
                    }
                }.launchIn(viewModelScope)
            }
        }
    }
}