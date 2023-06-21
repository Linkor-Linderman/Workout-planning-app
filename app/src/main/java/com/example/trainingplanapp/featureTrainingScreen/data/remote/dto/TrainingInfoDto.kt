package com.example.trainingplanapp.featureTrainingScreen.data.remote.dto

import com.example.trainingplanapp.featureTrainingScreen.domain.model.TrainingInfo

data class TrainingInfoDto(
    val common: Boolean,
    val description: String,
    val id: String,
    val name: String
) {
    fun toTrainingInfo(): TrainingInfo {
        return TrainingInfo(common, description, id, name)
    }
}