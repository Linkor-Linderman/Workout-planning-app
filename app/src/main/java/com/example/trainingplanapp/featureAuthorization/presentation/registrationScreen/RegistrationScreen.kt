package com.example.trainingplanapp.featureAuthorization.presentation.registrationScreen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.trainingplanapp.R
import com.example.trainingplanapp.common.ExceptionAlertDialog
import com.example.trainingplanapp.featureAuthorization.presentation.common.AuthorizationTextField
import com.example.trainingplanapp.featureAuthorization.presentation.common.CheckYourEmailDialog
import com.example.trainingplanapp.featureAuthorization.presentation.common.EmptyButton
import com.example.trainingplanapp.featureAuthorization.presentation.common.FilledButton
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun RegistrationScreen(
    navController: DestinationsNavigator,
    viewModel: RegistrationScreenViewModel = hiltViewModel()
) {

    val state by viewModel.container.stateFlow.collectAsState()
    LaunchedEffect(viewModel) {
        viewModel.container.sideEffectFlow.collect {
            when (it) {
                is RegistrationScreenSideEffects.Navigate -> {
                    navController.navigate(it.destination)
                }
                RegistrationScreenSideEffects.NavigateBack -> {
                    navController.popBackStack()
                }
            }
        }
    }
    var enabled by remember { mutableStateOf(false) }
    val alpha: Float by animateFloatAsState(if (enabled) 0.5f else 1f)
    LaunchedEffect(key1 = Unit) {
        enabled = true
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(alpha),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.gym_boss_icon_white_version_),
                contentDescription = "app_icon",
                colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
            )
        }
        if (state.errorMessage.isNotBlank()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(2f),
                contentAlignment = Alignment.Center
            ) {
                ExceptionAlertDialog(messageText = state.errorMessage) {
                    viewModel.onEvent(RegistrationScreenUiEvents.DismissAlertDialog)
                }
            }
        } else if (state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(2f),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(64.dp),
                    color = MaterialTheme.colors.primary
                )
            }
        } else if (state.applicationSubmitted) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(2f),
                contentAlignment = Alignment.Center
            ) {
                CheckYourEmailDialog() {
                    viewModel.onEvent(RegistrationScreenUiEvents.DismissConfirmDialog)
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState()),

                    ) {
                    AuthorizationTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = state.email,
                        placeholder = "Email",
                        label = "Email",
                        keyboardType = KeyboardType.Email,
                        onValueChange = {
                            viewModel.onEvent(
                                RegistrationScreenUiEvents.ChangeEmailValue(
                                    it
                                )
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    AuthorizationTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = state.login,
                        placeholder = "Login",
                        label = "Login",
                        keyboardType = KeyboardType.Text,
                        onValueChange = {
                            viewModel.onEvent(
                                RegistrationScreenUiEvents.ChangeLoginValue(
                                    it
                                )
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    AuthorizationTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = state.name,
                        placeholder = "Name",
                        label = "Name",
                        keyboardType = KeyboardType.Text,
                        onValueChange = {
                            viewModel.onEvent(
                                RegistrationScreenUiEvents.ChangeNameValue(
                                    it
                                )
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    AuthorizationTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = state.password,
                        placeholder = "Password",
                        label = "Password",
                        keyboardType = KeyboardType.Password,
                        visualTransformation = PasswordVisualTransformation(),
                        onValueChange = {
                            viewModel.onEvent(
                                RegistrationScreenUiEvents.ChangePasswordValue(
                                    it
                                )
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    AuthorizationTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = state.repeatPassword,
                        placeholder = "Repeat password",
                        label = "Repeat password",
                        keyboardType = KeyboardType.Password,
                        visualTransformation = PasswordVisualTransformation(),
                        onValueChange = {
                            viewModel.onEvent(
                                RegistrationScreenUiEvents.ChangeRepeatPasswordValue(
                                    it
                                )
                            )
                        }
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 16.dp)
                    .weight(0.5f),
                contentAlignment = Alignment.BottomCenter
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    FilledButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { viewModel.onEvent(RegistrationScreenUiEvents.ClickToSignupButton) },
                        text = "Sign-up"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    EmptyButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { viewModel.onEvent(RegistrationScreenUiEvents.ClickToSigninButton) },
                        text = "Sign-in"
                    )
                }
            }
        }
    }
}