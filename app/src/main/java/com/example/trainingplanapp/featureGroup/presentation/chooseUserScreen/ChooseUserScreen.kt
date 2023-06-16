package com.example.trainingplanapp.featureGroup.presentation.chooseUserScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.trainingplanapp.common.ExceptionAlertDialog
import com.example.trainingplanapp.featureGroup.domain.model.UserInfo
import com.example.trainingplanapp.featureGroup.presentation.composable.UserInfoWithoutButton
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.result.ResultBackNavigator

@Destination
@Composable
fun SearchUserScreen(
    resultNavigator: ResultBackNavigator<UserInfo>,
    viewModel: ChooseUserScreenViewModel = hiltViewModel(),
) {
    val state by viewModel.container.stateFlow.collectAsState()
    LaunchedEffect(viewModel) {
        viewModel.container.sideEffectFlow.collect {
            when (it) {
                is ChooseUserScreenSideEffects.NavigateBack -> {
                    resultNavigator.navigateBack(result = it.result)
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.align(Alignment.Start),
            text = "Search user",
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.primary,
            textAlign = TextAlign.Start,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(24.dp))
        TextField(
            value = state.name,
            onValueChange = {
                viewModel.onEvent(ChooseUserScreenUiEvents.OnSearchFieldChange(it))
            },
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.primary),
            placeholder = {
                Text(
                    text = "Search...",
                    style = MaterialTheme.typography.body2.copy(
                        color = MaterialTheme.colors.primary.copy(
                            alpha = 0.8f
                        )
                    ),
                )
            },
            trailingIcon = {
                Image(
                    imageVector = Icons.Default.Search,
                    contentDescription = "search_icon"
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colors.primary,
                cursorColor = MaterialTheme.colors.primary,
                backgroundColor = Color.Transparent,
                trailingIconColor = MaterialTheme.colors.primary
            )
        )
        Spacer(modifier = Modifier.height(24.dp))
        if (state.errorMessage.isNotBlank()) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                ExceptionAlertDialog(messageText = state.errorMessage) {
                    viewModel.onEvent(ChooseUserScreenUiEvents.DismissAlertDialog)
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
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.userList.size) { i ->
                    val userInfo = state.userList[i]
                    if (i > 0) {
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    UserInfoWithoutButton(
                        userInfo = userInfo
                    ) {
                        viewModel.onEvent(ChooseUserScreenUiEvents.OnChooseUser(it))
                    }
                }
            }
        }
    }
}