package com.example.trainingplanapp.featureExercises.presentation.allExercisesScreen

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.trainingplanapp.common.ExceptionAlertDialog
import com.example.trainingplanapp.featureExercises.presentation.composable.CommonExercisesPage
import com.example.trainingplanapp.featureExercises.presentation.composable.ComplexListPage
import com.example.trainingplanapp.featureExercises.presentation.composable.MyExercisesPage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AllExercisesScreen(
    destinationNavController: DestinationsNavigator,
    viewModel: AllExercisesScreenViewModel = hiltViewModel(),
    contentPaddingValues: PaddingValues
) {
    val state by viewModel.container.stateFlow.collectAsState()
    LaunchedEffect(viewModel) {
        viewModel.container.sideEffectFlow.collect {
            when (it) {
                is AllExercisesSideEffects.Navigate -> {
                    destinationNavController.navigate(it.destination)
                }
                is AllExercisesSideEffects.NavigateWithResult -> TODO()
                is AllExercisesSideEffects.NavigateWithArguments -> {
                    destinationNavController.navigate(it.destination)
                }
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        val pagerState = rememberPagerState(
            pageCount = 3,
            initialOffscreenLimit = 1,
            infiniteLoop = true,
            initialPage = 0,
        )
        val tabIndex = remember {
            mutableStateOf(pagerState.currentPage)
        }
        val coroutineScope = rememberCoroutineScope()
        val listOfTabsName = listOf("My exercises", "Set of Exercise", "Common exercise")
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPaddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier.align(Alignment.Start),
                text = "Exercises",
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.primary,
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(24.dp))
            TabRow(
                modifier = Modifier.fillMaxWidth(),
                // Our selected tab is our current page
                selectedTabIndex = pagerState.currentPage,
                // Override the indicator, using the provided pagerTabIndicatorOffset modifier
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage])
                    )
                },
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.primary
            ) {
                // Add tabs for all of our pages
                listOfTabsName.forEachIndexed { index, title ->
                    Tab(
                        text = {
                            Text(
                                text = title,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.h6,
                                color = MaterialTheme.colors.primary
                            )

                        },
                        selected = pagerState.currentPage == index,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(
                                    page = index,
                                    pageOffset = 0f,
                                    animationSpec = tween(100),
                                    skipPages = true
                                )
                            }
                        },
                    )
                }
            }
            if (state.errorMessage.isNotBlank()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    ExceptionAlertDialog(messageText = state.errorMessage) {
                        viewModel.onEvent(AllExercisesUiEvents.DismissAlertDialog)
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
                HorizontalPager(
                    state = pagerState,
                ) { page ->
                    when (page) {
                        0 -> MyExercisesPage(
                            listOfExercises = state.listOfMyExercises,
                            onExerciseClick = {
                                viewModel.onEvent(AllExercisesUiEvents.ClickToExercise(it))
                            },
                        )
                        1 -> ComplexListPage(
                            listOfComplex = state.listOfSetOfExercises,
                            onComplexInfoClick = {
                                viewModel.onEvent(AllExercisesUiEvents.ClickToComplex(it))
                            })
                        2 -> CommonExercisesPage(
                            listOfExercises = state.listOfCommonExercises,
                            onExerciseClick = {
                                viewModel.onEvent(AllExercisesUiEvents.ClickToExercise(it))
                            },
                        )
                    }
                }
            }
        }
    }
}



