package com.example.trainingplanapp.featureAuthorization.presentation.loginScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainingplanapp.R
import com.example.trainingplanapp.common.Resource
import com.example.trainingplanapp.destinations.MainScreenViewDestination
import com.example.trainingplanapp.destinations.RegistrationScreenDestination
import com.example.trainingplanapp.di.utill.StringResourcesManager
import com.example.trainingplanapp.featureAuthorization.domain.model.UserCredentials
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
class LoginScreenViewModel @Inject constructor(
    private val useCase: AuthUseCases,
    private val stringResourcesManager: StringResourcesManager
) : ContainerHost<LoginScreenState, LoginScreenSideEffects>, ViewModel() {
    private val unexpectedErrorMessage =
        stringResourcesManager.getStringResourceById(R.string.unexpected_error)

    override val container =
        container<LoginScreenState, LoginScreenSideEffects>(LoginScreenState())

    fun onEvent(event: LoginScreenUiEvents) {
        when (event) {
            is LoginScreenUiEvents.ChangeEmailValue -> intent {
                reduce {
                    state.copy(email = event.value)
                }
            }
            is LoginScreenUiEvents.ChangePasswordValue -> intent {
                reduce {
                    state.copy(password = event.value)
                }
            }
            LoginScreenUiEvents.ClickToLoginButton -> login()
            LoginScreenUiEvents.ClickToSigninButton -> intent {
                postSideEffect(
                    LoginScreenSideEffects.Navigate(
                        RegistrationScreenDestination
                    )
                )
            }
            LoginScreenUiEvents.DismissAlertDialog -> intent {
                reduce {
                    state.copy(errorMessage = "")
                }
            }
        }
    }

    private fun login() {
        intent {
            if (!useCase.emptyFieldsValidationUseCase(state.email, state.password)) {
                reduce { state.copy(errorMessage = stringResourcesManager.getStringResourceById(R.string.empty_validation)) }
            } else {
                useCase.loginUseCase(UserCredentials(state.email, state.password))
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
                                        isLoading = false,
                                        errorMessage = "",
                                    )
                                }
                                postSideEffect(
                                    LoginScreenSideEffects.Navigate(MainScreenViewDestination)
                                )
                            }
                        }
                    }.launchIn(viewModelScope)
            }
        }
    }

}