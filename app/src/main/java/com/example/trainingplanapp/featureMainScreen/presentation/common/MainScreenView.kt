package com.example.trainingplanapp.featureMainScreen.presentation.common

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun MainScreenView(
    destinationNavController: DestinationsNavigator,
) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) {
        it
        BottomNavigationGraph(
            navController = navController,
            destinationNavController = destinationNavController
        )
    }
}