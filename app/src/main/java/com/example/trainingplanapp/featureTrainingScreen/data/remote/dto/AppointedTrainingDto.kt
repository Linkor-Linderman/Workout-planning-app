package com.example.trainingplanapp.featureTrainingScreen.data.remote.dto

import com.example.trainingplanapp.featureTrainingScreen.domain.model.AppointedTraining

data class AppointedTrainingDto(
    val dates: List<Long>,
    val id: String,
    val name: String,
    val trainerName: String
) {
    fun toAppointedTraining(): AppointedTraining {
        return AppointedTraining(dates, id, name, trainerName)
    }
}