package com.example.trainingplanapp.featureAuthorization.presentation.registrationScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainingplanapp.R
import com.example.trainingplanapp.common.Resource
import com.example.trainingplanapp.di.utill.StringResourcesManager
import com.example.trainingplanapp.featureAuthorization.domain.model.RegistrationInfo
import com.example.trainingplanapp.featureAuthorization.domain.useCases.AuthUseCases
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
class RegistrationScreenViewModel @Inject constructor(
    private val useCase: AuthUseCases,
    private val stringResourcesManager: StringResourcesManager
) : ContainerHost<RegistrationScreenState, RegistrationScreenSideEffects>, ViewModel() {

    private val unexpectedErrorMessage =
        stringResourcesManager.getStringResourceById(R.string.unexpected_error)

    override val container =
        container<RegistrationScreenState, RegistrationScreenSideEffects>(RegistrationScreenState())

    fun onEvent(event: RegistrationScreenUiEvents) {
        when (event) {
            is RegistrationScreenUiEvents.ChangeEmailValue -> intent {
                reduce {
                    state.copy(email = event.value)
                }
            }
            is RegistrationScreenUiEvents.ChangePasswordValue -> intent {
                reduce {
                    state.copy(password = event.value)
                }
            }
            RegistrationScreenUiEvents.ClickToSignupButton -> signin()
            RegistrationScreenUiEvents.ClickToSigninButton -> intent {
                postSideEffect(RegistrationScreenSideEffects.NavigateBack)
            }
            RegistrationScreenUiEvents.DismissAlertDialog -> intent {
                reduce {
                    state.copy(errorMessage = "")
                }
            }
            is RegistrationScreenUiEvents.ChangeLoginValue -> intent {
                reduce {
                    state.copy(login = event.value)
                }
            }
            is RegistrationScreenUiEvents.ChangeNameValue -> intent {
                reduce {
                    state.copy(name = event.value)
                }
            }
            is RegistrationScreenUiEvents.ChangeRepeatPasswordValue -> intent {
                reduce {
                    state.copy(repeatPassword = event.value)
                }
            }
            RegistrationScreenUiEvents.DismissConfirmDialog -> intent {
                reduce {
                    state.copy(applicationSubmitted = false)
                }
            }
        }
    }

    private fun signin() {
        intent {
            if (!useCase.emptyFieldsValidationUseCase(
                    state.email,
                    state.password,
                    state.login,
                    state.repeatPassword,
                    state.name
                )
            ) {
                reduce { state.copy(errorMessage = stringResourcesManager.getStringResourceById(R.string.empty_validation)) }
            } else if (!useCase.emailValidationUseCase(state.email)) {
                reduce { state.copy(errorMessage = stringResourcesManager.getStringResourceById(R.string.email_incorrect)) }
            } else if (!useCase.loginValidationUseCase(state.login)) {
                reduce { state.copy(errorMessage = stringResourcesManager.getStringResourceById(R.string.login_validation_error)) }
            } else if (!useCase.passwordValidationUseCase(state.password)) {
                reduce { state.copy(errorMessage = stringResourcesManager.getStringResourceById(R.string.password_validation)) }
            } else if (state.password != state.repeatPassword) {
                reduce { state.copy(errorMessage = stringResourcesManager.getStringResourceById(R.string.password_must_match)) }
            } else {
                useCase.registrationUseCase(
                    RegistrationInfo(
                        state.email,
                        state.login,
                        state.name,
                        state.password
                    )
                )
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
                                        applicationSubmitted = true,
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