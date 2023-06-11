package com.example.trainingplanapp.featureAuthorization.presentation.loginScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.TopCenter
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
import com.example.trainingplanapp.featureAuthorization.presentation.common.EmptyButton
import com.example.trainingplanapp.featureAuthorization.presentation.common.FilledButton
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start=true)
@Composable
fun LoginScreen(
    navController: DestinationsNavigator,
    viewModel: LoginScreenViewModel = hiltViewModel()
) {
    val state by viewModel.container.stateFlow.collectAsState()
    LaunchedEffect(viewModel) {
        viewModel.container.sideEffectFlow.collect {
            when (it) {
                is LoginScreenSideEffects.Navigate -> {
                    navController.navigate(it.destination)
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            contentAlignment = Center
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
                contentAlignment = Center
            ) {
                ExceptionAlertDialog(messageText = state.errorMessage) {
                    viewModel.onEvent(LoginScreenUiEvents.DismissAlertDialog)
                }
            }
        }
        else if (state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(2f),
                contentAlignment = TopCenter
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(64.dp),
                    color = MaterialTheme.colors.primary
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentAlignment = Center
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    AuthorizationTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = state.email,
                        placeholder = "Login",
                        label = "Login",
                        keyboardType = KeyboardType.Text,
                        onValueChange = { viewModel.onEvent(LoginScreenUiEvents.ChangeEmailValue(it)) }
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
                                LoginScreenUiEvents.ChangePasswordValue(
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
                    .weight(1f),
                contentAlignment = BottomCenter
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    FilledButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { viewModel.onEvent(LoginScreenUiEvents.ClickToLoginButton) },
                        text = "Login"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    EmptyButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { viewModel.onEvent(LoginScreenUiEvents.ClickToSigninButton) },
                        text = "Sign-up"
                    )
                }
            }
        }
    }
}
