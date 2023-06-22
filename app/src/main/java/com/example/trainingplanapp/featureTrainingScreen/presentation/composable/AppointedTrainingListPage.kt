package com.example.trainingplanapp.featureTrainingScreen.presentation.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.trainingplanapp.R
import com.example.trainingplanapp.featureTrainingScreen.domain.model.AppointedTraining

@Composable
fun AppointedTrainingListPage(
    listOfTrainings: List<AppointedTraining>,
    onTrainingInfoClick: (AppointedTraining) -> Unit,
    onTrainCompleteClick: (AppointedTraining) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        if (listOfTrainings.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier.size(64.dp),
                        painter = painterResource(id = R.drawable.empty_list_icon),
                        contentDescription = "",
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "You do not have trainings in your list",
                        style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.primary),
                        textAlign = TextAlign.Center
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    listOfTrainings.forEach { complexInfo ->
                        AppointedTrainingInfoItem(
                            complexInfo,
                            onTrainCompleteClick = {
                                onTrainCompleteClick(it)
                            },
                            onCardClick = {
                                onTrainingInfoClick(it)
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}