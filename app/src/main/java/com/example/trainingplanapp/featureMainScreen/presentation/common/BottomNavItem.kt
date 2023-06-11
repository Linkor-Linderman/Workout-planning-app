package com.example.trainingplanapp.featureMainScreen.presentation.common

import com.example.trainingplanapp.R

sealed class BottomNavItem(var title: String, var icon: Int, var screen_route: String) {

    object Home : BottomNavItem("Home", R.drawable.home_icon, "home")
    object Trainings : BottomNavItem("Trainings", R.drawable.training_icon_cropped, "trainings")
    object Exercises : BottomNavItem("Exercises", R.drawable.exercise_icon_cropped, "exercises")
    object Groups : BottomNavItem("Groups", R.drawable.group_icon, "groups")
}
