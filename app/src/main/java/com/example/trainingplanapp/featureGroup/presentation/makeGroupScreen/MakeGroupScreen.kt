package com.example.trainingplanapp.featureGroup.presentation.makeGroupScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.trainingplanapp.common.ExceptionAlertDialog
import com.example.trainingplanapp.destinations.SearchUserScreenDestination
import com.example.trainingplanapp.featureGroup.domain.model.UserInfo
import com.example.trainingplanapp.featureGroup.presentation.composable.UserInfoItem
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.NavResult
import com.ramcosta.composedestinations.result.ResultRecipient

@Destination
@Composable
fun MakeGroupScreen(
    destinationNavController: DestinationsNavigator,
    resultRecipient: ResultRecipient<SearchUserScreenDestination, UserInfo>,
    viewModel: MakeGroupScreenViewModel = hiltViewModel(),
) {
    val state by viewModel.container.stateFlow.collectAsState()
    LaunchedEffect(viewModel) {
        viewModel.container.sideEffectFlow.collect {
            when (it) {
                is MakeGroupSideEffects.Navigate -> {
                    destinationNavController.navigate(it.destination)
                }
                MakeGroupSideEffects.NavigateBack -> {
                    destinationNavController.popBackStack()
                }
            }
        }
    }
    resultRecipient.onNavResult { result ->
        when (result) {
            is NavResult.Canceled -> {

            }
            is NavResult.Value -> {
                viewModel.addUserInList(result.value)
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Make group",
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.primary,
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = {
                viewModel.onEvent(MakeGroupScreenUiEvents.ClickToConfirmCreating)
            }) {
                Image(
                    imageVector = Icons.Default.Check,
                    colorFilter = ColorFilter.tint(MaterialTheme.colors.primary),
                    contentDescription = "confirm"
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        if (state.errorMessage.isNotBlank()) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                ExceptionAlertDialog(messageText = state.errorMessage) {
                    viewModel.onEvent(MakeGroupScreenUiEvents.DismissAlertDialog)
                }
            }
        } else if (state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(64.dp),
                    color = MaterialTheme.colors.primary
                )
            }
        } else {
            Text(
                modifier = Modifier.align(Alignment.Start),
                text = "Name",
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.primary,
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = state.name,
                onValueChange = {
                    viewModel.onEvent(MakeGroupScreenUiEvents.OnNameChange(it))
                },
                modifier = Modifier.fillMaxWidth(),
                textStyle = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.primary),
                placeholder = {
                    Text(
                        text = "Name...",
                        style = MaterialTheme.typography.body2.copy(
                            color = MaterialTheme.colors.primary.copy(
                                alpha = 0.8f
                            )
                        ),
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = MaterialTheme.colors.primary,
                    cursorColor = MaterialTheme.colors.primary,
                    backgroundColor = Color.Transparent,
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier.align(Alignment.Start),
                text = "Description",
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.primary,
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.description,
                onValueChange = {
                    viewModel.onEvent(MakeGroupScreenUiEvents.OnDescriptionChange(it))
                },
                singleLine = false,
                maxLines = 6,
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = MaterialTheme.colors.primary,
                    cursorColor = MaterialTheme.colors.primary,
                    backgroundColor = Color.Transparent,
                ),
                placeholder = {
                    Text(
                        text = "Description..,",
                        color = MaterialTheme.colors.primaryVariant,
                        fontSize = 14.sp
                    )
                },
                textStyle = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.primary),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Users",
                    style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.primary)
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = {
                    viewModel.onEvent(MakeGroupScreenUiEvents.ClickToAddUser)
                }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "add_user",
                        tint = MaterialTheme.colors.primary
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            state.userInfoList.forEach {
                UserInfoItem(userInfo = it, deleteFromList = { user ->
                    viewModel.onEvent(MakeGroupScreenUiEvents.OnDeleteUserClick(user))
                })
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}