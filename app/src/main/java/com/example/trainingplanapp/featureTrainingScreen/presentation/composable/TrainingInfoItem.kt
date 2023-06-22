package com.example.trainingplanapp.featureTrainingScreen.presentation.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.trainingplanapp.featureTrainingScreen.domain.model.AppointedTraining
import com.example.trainingplanapp.featureTrainingScreen.domain.model.TrainingInfo
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun TrainingInfoItem(
    trainingInfo: TrainingInfo,
    onCardClick: (TrainingInfo) -> Unit,
    onCompleteTrainClick: (TrainingInfo) -> Unit
) {
    Card(
        modifier = Modifier
            .height(128.dp)
            .clickable {
                onCardClick(trainingInfo)
            },
        backgroundColor = MaterialTheme.colors.secondary,
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = trainingInfo.name,
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.primary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        start = 8.dp,
                        top = 4.dp,
                        bottom = 4.dp,
                        end = 4.dp
                    )
            ) {
                Text(
                    text = "Description:",
                    style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight(700)),
                    color = MaterialTheme.colors.primary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = trainingInfo.description,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.primary.copy(alpha = 0.8f),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.clickable {
                        onCompleteTrainClick(trainingInfo)
                    },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Complete train",
                        style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight(700)),
                        color = MaterialTheme.colors.primary,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.width(1.dp))
                    Icon(
                        modifier = Modifier.size(16.dp),
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "",
                        tint = MaterialTheme.colors.primary
                    )
                }
            }
        }
    }
}

@Composable
fun AppointedTrainingInfoItem(
    trainingInfo: AppointedTraining,
    onCardClick: (AppointedTraining) -> Unit,
    onTrainCompleteClick: (AppointedTraining) -> Unit
) {
    val dates: String = buildString {
        for (i in trainingInfo.dates.indices) {
            if (i != trainingInfo.dates.size - 1)
                append(convertDateString(trainingInfo.dates[i]) + ", ")
            else
                append(convertDateString(trainingInfo.dates[i]))
        }
        trainingInfo.dates
    }
    Card(
        modifier = Modifier
            .height(128.dp)
            .clickable {
                onCardClick(trainingInfo)
            },
        backgroundColor = MaterialTheme.colors.secondary,
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 8.dp)
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = trainingInfo.name,
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.primary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        start = 8.dp,
                        top = 4.dp,
                        bottom = 4.dp,
                        end = 4.dp
                    ),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Coach name: " + trainingInfo.trainerName,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.primary,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Dates: $dates",
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.primary.copy(alpha = 0.8f),
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.width(16.dp))
                Row(
                    modifier = Modifier.clickable {
                        onTrainCompleteClick(trainingInfo)
                    },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Complete train",
                        style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight(700)),
                        color = MaterialTheme.colors.primary,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.width(1.dp))
                    Icon(
                        modifier = Modifier.size(16.dp),
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "",
                        tint = MaterialTheme.colors.primary
                    )
                }
            }
        }
    }
}

fun convertDateString(timestamp: Long): String {
    val date = Date(timestamp)
    val format = SimpleDateFormat("dd.MM.yyyy")
    return format.format(date)
}