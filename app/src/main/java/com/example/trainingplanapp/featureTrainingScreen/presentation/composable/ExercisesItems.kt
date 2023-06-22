package com.example.trainingplanapp.featureTrainingScreen.presentation.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.trainingplanapp.R
import com.example.trainingplanapp.featureTrainingScreen.domain.model.trainingInfo.ComplexInTrainExtended
import com.example.trainingplanapp.featureTrainingScreen.domain.model.trainingInfo.ExerciseInTrain

@Composable
fun ComplexInTrainItem(
    complex: ComplexInTrainExtended,
    onCardClick: (ComplexInTrainExtended) -> Unit
) {
    Card(
        modifier = Modifier
            .clickable {
                onCardClick(complex)
            },
        backgroundColor = MaterialTheme.colors.secondary,
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = complex.name,
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.primary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(16.dp))
            complex.exerciseValues.forEach {
                ExerciseInTrainItem(
                    exercise = it,
                    onCardClick = {}
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun ExerciseInTrainItem(
    exercise: ExerciseInTrain,
    onCardClick: (ExerciseInTrain) -> Unit
) {
    Card(
        modifier = Modifier
            .height(128.dp)
            .clickable {
                onCardClick(exercise)
            },
        backgroundColor = MaterialTheme.colors.secondary,
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = exercise.name,
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.primary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Image(
                        modifier = Modifier.size(100.dp),
                        painter = painterResource(id = R.drawable.sitting_example),
                        contentDescription = "",
                        contentScale = ContentScale.Crop
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
                        text = "Duration: " + exercise.exerciseValues.duration,
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.primary,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Repetitions: " + exercise.exerciseValues.repetitions,
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.primary,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Weight: " + exercise.exerciseValues.weight,
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.primary,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
fun ComplexInTrainItemWithCompleteButton(
    complex: ComplexInTrainExtended,
    onCompleteComplexClick: (ComplexInTrainExtended) -> Unit,
    onCompleteExerciseClick: (ComplexInTrainExtended, ExerciseInTrain) -> Unit
) {
    Card(
        modifier = Modifier,
        backgroundColor = MaterialTheme.colors.secondary,
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = complex.name,
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.primary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(16.dp))
            complex.exerciseValues.forEach { exercise ->
                ExerciseInTrainItemWithCompleteButton(
                    exercise = exercise
                ) {
                    onCompleteExerciseClick(complex, exercise)
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.clickable {
                    onCompleteComplexClick(complex)
                },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Complete complex",
                    style = MaterialTheme.typography.button,
                    color = MaterialTheme.colors.primary,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.Default.Check,
                    modifier = Modifier.size(16.dp),
                    tint = MaterialTheme.colors.primary,
                    contentDescription = "")
            }
        }
    }
}

@Composable
fun ExerciseInTrainItemWithCompleteButton(
    exercise: ExerciseInTrain,
    onCompleteButtonClick: (ExerciseInTrain) -> Unit
) {
    Card(
        modifier = Modifier
            .height(140.dp),
        backgroundColor = MaterialTheme.colors.secondary,
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = exercise.name,
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.primary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Image(
                        modifier = Modifier.size(100.dp),
                        painter = painterResource(id = R.drawable.sitting_example),
                        contentDescription = "",
                        contentScale = ContentScale.Crop
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
                        text = "Duration: " + exercise.exerciseValues.duration,
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.primary,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Repetitions: " + exercise.exerciseValues.repetitions,
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.primary,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Weight: " + exercise.exerciseValues.weight,
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.primary,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.clickable {
                            onCompleteButtonClick(exercise)
                        },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Complete",
                            style = MaterialTheme.typography.button,
                            color = MaterialTheme.colors.primary,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            modifier = Modifier.size(16.dp),
                            tint = MaterialTheme.colors.primary,
                            imageVector = Icons.Default.Check,
                            contentDescription = ""
                        )
                    }
                }
            }
        }
    }
}