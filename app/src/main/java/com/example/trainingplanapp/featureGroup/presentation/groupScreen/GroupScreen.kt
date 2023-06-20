package com.example.trainingplanapp.featureGroup.presentation.groupScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.trainingplanapp.common.ExceptionAlertDialog
import com.example.trainingplanapp.featureGroup.presentation.composable.ChooseButton
import com.example.trainingplanapp.featureGroup.presentation.composable.GroupScreenForCoach
import com.example.trainingplanapp.featureGroup.presentation.composable.GroupScreenForUser
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Composable
fun GroupScreen(
    destinationNavController: DestinationsNavigator,
    viewModel: GroupScreenViewModel = hiltViewModel(),
    contentPaddingValues: PaddingValues
) {
    val state by viewModel.container.stateFlow.collectAsState()
    LaunchedEffect(viewModel) {
        viewModel.container.sideEffectFlow.collect {
            when (it) {
                is GroupScreenSideEffect.Navigate -> {
                    destinationNavController.navigate(it.destination)
                }
                is GroupScreenSideEffect.NavigateWithResult -> TODO()
                is GroupScreenSideEffect.NavigateWithArguments -> {
                    destinationNavController.navigate(it.destination)
                }
            }
        }
    }
    LaunchedEffect(Unit) {
        viewModel.checkIsUserCoach()
        viewModel.fetchMyGroup()
    }
    if (state.errorMessage.isNotBlank()) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            ExceptionAlertDialog(messageText = state.errorMessage) {
                viewModel.onEvent(GroupScreenUiEvents.DismissAlertDialog)
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPaddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier.align(Alignment.Start),
                text = "Groups",
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.primary,
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(24.dp))
            ChooseButton(
                isFirstButtonChosen = state.isFirstButtonChosen,
                onFirstButtonClicked = {
                    viewModel.onEvent(GroupScreenUiEvents.ClickToCoachButton)
                },
                onSecondButtonClicked = {
                    viewModel.onEvent(GroupScreenUiEvents.ClickToGymGoerButton)
                }
            )
            if (state.isFirstButtonChosen) {
                GroupScreenForCoach(
                    onBecomeCoachButtonClick = { viewModel.onEvent(GroupScreenUiEvents.ClickToBecomeCoachButton) },
                    onAddGroupButtonClicked = { viewModel.onEvent(GroupScreenUiEvents.ClickToMakeGroupButton) },
                    onDeleteGroupClick = {
                        viewModel.onEvent(
                            GroupScreenUiEvents.ClickToDeleteGroup(
                                it
                            )
                        )
                    },
                    onEditGroupClick = {
                        viewModel.onEvent(
                            GroupScreenUiEvents.ClickToEditGroup(
                                it
                            )
                        )
                    },
                    onAddTrainClick = {
                        viewModel.onEvent(
                            GroupScreenUiEvents.ClickToAddTrainGroup(
                                it
                            )
                        )
                    },
                    groupInfoList = state.subordinateGroups,
                    isCoach = state.isCoach,
                    onConfirmRequestsButtonClick = { viewModel.onEvent(GroupScreenUiEvents.ClickToConfirmRequestsButton) })
            } else {
                GroupScreenForUser(
                    onJoinCoachButtonClicked = { viewModel.onEvent(GroupScreenUiEvents.ClickToAddToCoachButton) },
                    groupInfoList = state.yourGroups,
                    onCardClick = {}
                )
            }
        }
    }
}