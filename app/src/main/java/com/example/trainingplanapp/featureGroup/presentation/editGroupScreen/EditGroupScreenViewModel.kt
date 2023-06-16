package com.example.trainingplanapp.featureGroup.presentation.editGroupScreen

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
class EditGroupScreenViewModel @Inject constructor(
    private val groupUseCase: GroupUseCases,
    private val authUseCase: AuthUseCases,
    private val stringResourcesManager: StringResourcesManager
) : ContainerHost<EditGroupScreenState, EditGroupSideEffects>, ViewModel() {
    private val unexpectedErrorMessage =
        stringResourcesManager.getStringResourceById(R.string.unexpected_error)

    override val container =
        container<EditGroupScreenState, EditGroupSideEffects>(EditGroupScreenState())

    fun onEvent(event: EditGroupScreenUiEvents) {
        when (event) {
            EditGroupScreenUiEvents.ClickToAddUser -> intent {
                postSideEffect(EditGroupSideEffects.Navigate(SearchUserScreenDestination))
            }
            EditGroupScreenUiEvents.ClickToConfirmEditing -> editGroup()
            EditGroupScreenUiEvents.DismissAlertDialog -> intent {
                reduce {
                    state.copy(errorMessage = "")
                }
            }
            is EditGroupScreenUiEvents.OnDeleteUserClick -> intent {
                reduce {
                    state.copy(userInfoList = state.userInfoList.filter { it.id != event.user.id })
                }
            }
            is EditGroupScreenUiEvents.OnDescriptionChange -> intent {
                reduce {
                    state.copy(description = event.value)
                }
            }
            is EditGroupScreenUiEvents.OnNameChange -> intent {
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

    private fun editGroup() {
        intent {
            if (!authUseCase.emptyFieldsValidationUseCase(state.name, state.description)) {
                reduce { state.copy(errorMessage = stringResourcesManager.getStringResourceById(R.string.empty_validation)) }
            } else {
                groupUseCase.editGroupUseCase(
                    state.groupId,
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
                            postSideEffect(EditGroupSideEffects.NavigateBack)
                        }
                    }
                }.launchIn(viewModelScope)
            }
        }
    }

    fun getGroupById(
        groupId: String
    ) {
        intent {
            groupUseCase.getGroupInfoById(groupId)
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
                                    name = result.data!!.name,
                                    description = result.data.description,
                                    userInfoList = result.data.users,
                                    groupId = groupId,
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