package com.example.trainingplanapp.featureMainScreen.presentation.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.trainingplanapp.featureExercises.presentation.allExercisesScreen.AllExercisesScreen
import com.example.trainingplanapp.featureGroup.presentation.groupScreen.GroupScreen
import com.example.trainingplanapp.featureMainScreen.presentation.mainScreen.MainScreen
import com.example.trainingplanapp.featureTrainingScreen.presentation.allTrainingsScreen.AllTrainingsScreen
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun BottomNavigationGraph(
    navController: NavHostController,
    destinationNavController: DestinationsNavigator,
    paddingValues: PaddingValues
) {
    NavHost(navController, startDestination = BottomNavItem.Home.screen_route) {
        composable(BottomNavItem.Home.screen_route) {
            MainScreen(
                destinationNavController,
                contentPaddingValues = paddingValues
            )
        }
        composable(BottomNavItem.Trainings.screen_route) {
            AllTrainingsScreen(
                destinationNavController,
                contentPaddingValues = paddingValues
            )
        }
        composable(BottomNavItem.Exercises.screen_route) {
            AllExercisesScreen(
                destinationNavController,
                contentPaddingValues = paddingValues
            )
        }
        composable(BottomNavItem.Groups.screen_route) {
            GroupScreen(
                destinationNavController,
                contentPaddingValues = paddingValues
            )
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Trainings,
        BottomNavItem.Exercises,
        BottomNavItem.Groups,
    )
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.primary
    ) {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry.value?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.subtitle2
                    )
                },
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = MaterialTheme.colors.primary.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.screen_route,
                onClick = {
                    navController.navigate(item.screen_route) {

                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}