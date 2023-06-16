package com.example.trainingplanapp.featureGroup.presentation.confirmRequestScreen

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
import com.example.trainingplanapp.featureGroup.presentation.composable.RequestInfoItem
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun ConfirmRequestsScreen(
    destinationNavController: DestinationsNavigator,
    viewModel: ConfirmRequestsScreenViewModel = hiltViewModel(),
) {
    val state by viewModel.container.stateFlow.collectAsState()
    LaunchedEffect(viewModel) {
        viewModel.container.sideEffectFlow.collect {
            when (it) {
                is ConfirmRequestsScreenSideEffects.Navigate -> {
                    destinationNavController.navigate(it.destination)
                }
                ConfirmRequestsScreenSideEffects.NavigateBack -> {
                    destinationNavController.popBackStack()
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
            text = "Requests",
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
                viewModel.onEvent(ConfirmRequestsScreenUiEvents.OnSearchFieldChange(it))
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
                    viewModel.onEvent(ConfirmRequestsScreenUiEvents.DismissAlertDialog)
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
                items(state.requestList.size) { i ->
                    val requestInfo = state.requestList[i]
                    if (i > 0) {
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    RequestInfoItem(
                        requestInfo = requestInfo,
                        onAcceptButtonClick = {
                            viewModel.onEvent(ConfirmRequestsScreenUiEvents.OnAcceptRequest(it))
                        }
                    ) {
                        viewModel.onEvent(ConfirmRequestsScreenUiEvents.OnDismissRequest(it))
                    }
                }
            }
        }
    }
}