package com.example.trainingplanapp.featureExercises.presentation.composable

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.trainingplanapp.R
import com.example.trainingplanapp.featureExercises.domain.model.Exercise
import com.example.trainingplanapp.featureExercises.domain.model.ExerciseInfo

@Composable
fun ExerciseInfoItem(
    exerciseInfo: ExerciseInfo,
    onCardClick: (ExerciseInfo) -> Unit
) {
    val bodyParts = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight(700)
            )
        ) {
            for (i in exerciseInfo.muscleGroup.indices) {
                if (i != exerciseInfo.muscleGroup.size - 1)
                    append("${exerciseInfo.muscleGroup[i]}, ")
                else
                    append(exerciseInfo.muscleGroup[i])
            }
        }
    }
    // val emptyPlaceHolder = getBitmapFromDrawable(LocalContext.current, R.drawable.sitting_example)
    // val imageBitmap = emptyPlaceHolder.asImageBitmap()

    Card(
        modifier = Modifier
            .height(148.dp)
            .clickable {
                onCardClick(exerciseInfo)
            },
        backgroundColor = MaterialTheme.colors.secondary,
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            if (exerciseInfo.bitmap == null) {
                Image(
                    painter = painterResource(id = R.drawable.sitting_example),
                    modifier = Modifier
                        .weight(1f)
                        .padding(
                            top = 4.dp,
                            bottom = 4.dp,
                            start = 4.dp
                        )
                        .clip(RoundedCornerShape(4.dp)),
                    contentDescription = "Train_cover",
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    modifier = Modifier
                        .weight(1f)
                        .padding(
                            top = 4.dp,
                            bottom = 4.dp,
                            start = 4.dp
                        )
                        .clip(RoundedCornerShape(4.dp)),
                    bitmap = exerciseInfo.bitmap.asImageBitmap(),
                    contentDescription = "Train_cover",
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
                    text = exerciseInfo.name,
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.primary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Body parts: $bodyParts",
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.primary,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}

@Composable
fun ExerciseItem(
    exerciseInfo: Exercise,
    onCardClick: (Exercise) -> Unit
) {
    val bodyParts = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight(700)
            )
        ) {
            for (i in exerciseInfo.muscleGroups.indices) {
                if (i != exerciseInfo.muscleGroups.size - 1)
                    append("${exerciseInfo.muscleGroups[i]}, ")
                else
                    append(exerciseInfo.muscleGroups[i])
            }
        }
    }
    Card(
        modifier = Modifier
            .height(148.dp)
            .clickable {
                onCardClick(exerciseInfo)
            },
        backgroundColor = MaterialTheme.colors.secondary,
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        top = 4.dp,
                        bottom = 4.dp,
                        start = 4.dp
                    )
                    .clip(RoundedCornerShape(4.dp)),
                painter = painterResource(id = R.drawable.sitting_example),
                contentDescription = "Train_cover",
                contentScale = ContentScale.Crop
            )
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
                    text = exerciseInfo.name,
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.primary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = exerciseInfo.name,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.primary.copy(alpha = 0.8f),
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Body parts: $bodyParts",
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.primary.copy(alpha = 0.8f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}

@Composable
fun BodyPartItem(
    name: String
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(
                MaterialTheme.colors.primary
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(4.dp),
            text = name,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.background
        )
    }
}

fun getBitmapFromDrawable(context: Context, drawableId: Int): Bitmap {
    return BitmapFactory.decodeResource(context.resources, drawableId)
}
