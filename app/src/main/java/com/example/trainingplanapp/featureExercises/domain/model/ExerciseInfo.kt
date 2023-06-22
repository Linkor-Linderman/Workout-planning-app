package com.example.trainingplanapp.featureExercises.domain.model

import android.graphics.Bitmap

data class ExerciseInfo(
    val id: String,
    val imageId: String?,
    val bitmap: Bitmap?,
    val muscleGroup: List<String>,
    val name: String
)