package com.example.trainingplanapp.featureTrainingScreen.presentation.trainInfoScreen

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
import androidx.compose.material.icons.filled.PlayArrow
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
import com.example.trainingplanapp.featureTrainingScreen.domain.model.trainingInfo.ComplexInTrainExtended
import com.example.trainingplanapp.featureTrainingScreen.domain.model.trainingInfo.ExerciseInTrain
import com.example.trainingplanapp.featureTrainingScreen.presentation.composable.ComplexInTrainItem
import com.example.trainingplanapp.featureTrainingScreen.presentation.composable.ExerciseInTrainItem
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun TrainInfoScreen(
    destinationNavController: DestinationsNavigator,
    viewModel: TrainingInfoScreenViewModel = hiltViewModel(),
    trainId: String,
    isAppointed: Boolean
) {
    val state by viewModel.container.stateFlow.collectAsState()
    LaunchedEffect(viewModel) {
        viewModel.fetchTrainById(trainId, isAppointed)
        viewModel.container.sideEffectFlow.collect {
            when (it) {
                is TrainInfoSideEffects.Navigate -> {
                    destinationNavController.navigate(it.destination)
                }
                is TrainInfoSideEffects.NavigateWithResult -> TODO()
                is TrainInfoSideEffects.NavigateWithArguments -> {
                    destinationNavController.navigate(it.destination)
                }
                TrainInfoSideEffects.NavigateBack -> {
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
                viewModel.onEvent(TrainInfoUiEvents.DismissAlertDialog)
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
                        viewModel.onEvent(TrainInfoUiEvents.ClickToNavigateBack)
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
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = {
                        viewModel.onEvent(TrainInfoUiEvents.ClickToPlay)
                    }
                ) {
                    Image(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "",
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Description",
                    style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.primary),
                    textAlign = TextAlign.Start,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = state.training?.description ?: "",
                    style = MaterialTheme.typography.h6.copy(
                        color = MaterialTheme.colors.primary.copy(
                            0.8f
                        )
                    ),
                    textAlign = TextAlign.Start,
                    maxLines = 6,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Exercises:",
                    style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.primary),
                    textAlign = TextAlign.Start,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                state.listOfTrainingListItem.forEach {
                    when (it) {
                        is ComplexInTrainExtended -> {
                            ComplexInTrainItem(complex = it, onCardClick = {
                            })
                        }
                        is ExerciseInTrain -> {
                            ExerciseInTrainItem(exercise = it, onCardClick = {

                            })
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}