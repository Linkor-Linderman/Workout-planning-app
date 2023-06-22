package com.example.trainingplanapp.featureTrainingScreen.presentation.playTrainScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.trainingplanapp.common.ExceptionAlertDialog
import com.example.trainingplanapp.featureAuthorization.presentation.common.FilledButton
import com.example.trainingplanapp.featureTrainingScreen.domain.model.trainingInfo.ComplexInTrainExtended
import com.example.trainingplanapp.featureTrainingScreen.domain.model.trainingInfo.ExerciseInTrain
import com.example.trainingplanapp.featureTrainingScreen.presentation.composable.ComplexInTrainItemWithCompleteButton
import com.example.trainingplanapp.featureTrainingScreen.presentation.composable.ExerciseInTrainItemWithCompleteButton
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun PlayTrainScreen(
    destinationNavController: DestinationsNavigator,
    viewModel: PlayTrainScreenViewModel = hiltViewModel(),
    trainId: String,
    isAppointed: Boolean
) {
    val state by viewModel.container.stateFlow.collectAsState()
    LaunchedEffect(viewModel) {
        viewModel.fetchTrainById(trainId, isAppointed)
        viewModel.container.sideEffectFlow.collect {
            when (it) {
                is PlayTrainSideEffects.Navigate -> {
                    destinationNavController.navigate(it.destination)
                }
                is PlayTrainSideEffects.NavigateWithResult -> TODO()
                is PlayTrainSideEffects.NavigateWithArguments -> {
                    destinationNavController.navigate(it.destination)
                }
                PlayTrainSideEffects.NavigateBack -> {
                    destinationNavController.popBackStack()
                }
            }
        }
    }
    if (state.errorMessage.isNotBlank()) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            ExceptionAlertDialog(messageText = state.errorMessage) {
                viewModel.onEvent(PlayTrainInfoUiEvents.DismissAlertDialog)
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
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    modifier = Modifier,
                    onClick = {
                        viewModel.onEvent(PlayTrainInfoUiEvents.ClickToNavigateBack)
                    },
                ) {
                    Image(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "",
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = state.training?.name ?: "",
                    style = MaterialTheme.typography.h4.copy(color = MaterialTheme.colors.primary),
                    textAlign = TextAlign.Start,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            if (state.listOfTrainingListItem.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Train completed!",
                        style = MaterialTheme.typography.h4.copy(color = MaterialTheme.colors.primary),
                        textAlign = TextAlign.Center,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(modifier = Modifier.height(24.dp))
                    state.listOfTrainingListItem.forEach {
                        when (it) {
                            is ComplexInTrainExtended -> {
                                ComplexInTrainItemWithCompleteButton(
                                    complex = it,
                                    onCompleteComplexClick = {
                                        viewModel.onEvent(
                                            PlayTrainInfoUiEvents.OnComplexCompleteButtonClick(
                                                it
                                            )
                                        )
                                    }
                                ) { complexInTrainExtended, exerciseInTrain ->
                                    viewModel.onEvent(
                                        PlayTrainInfoUiEvents.OnExerciseCompleteInComplexButtonClick(
                                            complexInTrainExtended,
                                            exerciseInTrain
                                        )
                                    )
                                }
                            }
                            is ExerciseInTrain -> {
                                ExerciseInTrainItemWithCompleteButton(exercise = it) {
                                    viewModel.onEvent(
                                        PlayTrainInfoUiEvents.OnExerciseCompleteButtonClick(
                                            it
                                        )
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            FilledButton(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                text = "Complete train",
                onClick = {
                    viewModel.onEvent(PlayTrainInfoUiEvents.ClickToCompleteAll)
                }
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}