package com.example.trainingplanapp.featureExercises.presentation.exerciseInfoScreen

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.trainingplanapp.R
import com.example.trainingplanapp.common.ExceptionAlertDialog
import com.example.trainingplanapp.featureExercises.presentation.composable.BodyPartItem
import com.google.accompanist.flowlayout.FlowRow
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun ExerciseInfoScreen(
    exerciseId: String,
    destinationNavController: DestinationsNavigator,
    viewModel: ExerciseInfoScreenViewModel = hiltViewModel()
) {
    val state by viewModel.container.stateFlow.collectAsState()
    LaunchedEffect(viewModel) {
        viewModel.fetchExercise(exerciseId)
        viewModel.container.sideEffectFlow.collect {
            when (it) {
                is ExerciseInfoSideEffects.Navigate -> {
                    destinationNavController.navigate(it.destination)
                }
                is ExerciseInfoSideEffects.NavigateWithResult -> TODO()
                is ExerciseInfoSideEffects.NavigateWithArguments -> {
                    destinationNavController.navigate(it.destination)
                }
                ExerciseInfoSideEffects.NavigateBack -> {
                    destinationNavController.popBackStack()
                }
            }
        }
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        if (state.errorMessage.isNotBlank()) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                ExceptionAlertDialog(messageText = state.errorMessage) {
                    viewModel.onEvent(ExerciseInfoUiEvents.DismissAlertDialog)
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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopStart)
                        .height(256.dp),
                    painter = painterResource(id = R.drawable.sitting_example),
                    contentScale = ContentScale.Crop,
                    contentDescription = "exercise_cover"
                )
                IconButton(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(top = 16.dp),
                    onClick = {
                        viewModel.onEvent(ExerciseInfoUiEvents.OnBackArrowButtonClicked)
                    },
                ) {
                    Image(
                        imageVector = Icons.Default.ArrowBack,
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
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = state.exercise.name,
                    style = MaterialTheme.typography.h4.copy(color = MaterialTheme.colors.primary),
                    textAlign = TextAlign.Start,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(16.dp))
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    mainAxisSpacing = 4.dp,
                    crossAxisSpacing = 4.dp
                ) {
                    state.exercise.muscleGroups.forEach {
                        BodyPartItem(name = it)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Description",
                    style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.primary),
                    textAlign = TextAlign.Start,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = state.exercise.description,
                    style = MaterialTheme.typography.body1.copy(
                        color = MaterialTheme.colors.primary.copy(
                            0.8f
                        )
                    ),
                    textAlign = TextAlign.Start,
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Default value",
                    style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.primary),
                    textAlign = TextAlign.Start,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Duration: ${state.exercise.defaultValues.duration}",
                    style = MaterialTheme.typography.h6.copy(
                        color = MaterialTheme.colors.primary.copy(
                            0.9f
                        )
                    ),
                    textAlign = TextAlign.Start,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Repetitions: ${state.exercise.defaultValues.duration}",
                    style = MaterialTheme.typography.h6.copy(
                        color = MaterialTheme.colors.primary.copy(
                            0.9f
                        )
                    ),
                    textAlign = TextAlign.Start,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Weight: ${state.exercise.defaultValues.duration}",
                    style = MaterialTheme.typography.h6.copy(
                        color = MaterialTheme.colors.primary.copy(
                            0.9f
                        )
                    ),
                    textAlign = TextAlign.Start,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}