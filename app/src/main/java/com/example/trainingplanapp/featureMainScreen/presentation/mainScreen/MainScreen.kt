package com.example.trainingplanapp.featureMainScreen.presentation.mainScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.trainingplanapp.R
import com.example.trainingplanapp.common.ExceptionAlertDialog
import com.example.trainingplanapp.featureMainScreen.presentation.common.TrainingItemCard
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun MainScreen(
    destinationNavController: DestinationsNavigator,
    viewModel: MainScreenViewModel = hiltViewModel(),
    contentPaddingValues: PaddingValues
) {
    val state by viewModel.container.stateFlow.collectAsState()
    LaunchedEffect(viewModel) {
        viewModel.container.sideEffectFlow.collect {
            when (it) {
                is MainScreenSideEffects.Navigate -> {
                    destinationNavController.navigate(it.destination)
                }
                MainScreenSideEffects.NavigateBack -> {
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
                viewModel.onEvent(MainScreenUiEvents.DismissAlertDialog)
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
                .verticalScroll(rememberScrollState())
                .padding(contentPaddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier.align(Alignment.Start),
                text = state.currentDate,
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.secondary,
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                modifier = Modifier.align(Alignment.Start),
                text = "Hello, ${state.name}",
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.primary,
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(24.dp))
            if (state.currentDayTrainings.isEmpty()) {
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    modifier = Modifier
                        .size(128.dp)
                        .align(CenterHorizontally),
                    painter = painterResource(id = R.drawable.rest_icon),
                    contentDescription = "rest_icon",
                    colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "You don't have practice today",
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.primaryVariant,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis,
                )
                //Some button
                Spacer(modifier = Modifier.weight(1f))
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "Your today's workouts",
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.primary,
                        textAlign = TextAlign.Start,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        modifier = Modifier.clickable {
                            viewModel.onEvent(MainScreenUiEvents.ClickToSeeAll)
                        },
                        text = "See all",
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.primary,
                        textAlign = TextAlign.Start,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                state.currentDayTrainings.forEach { _ ->
                    TrainingItemCard() {
                        viewModel.onEvent(MainScreenUiEvents.ClickToTraining(it))
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

        }
    }
}