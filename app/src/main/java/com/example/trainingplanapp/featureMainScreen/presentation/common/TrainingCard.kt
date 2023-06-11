package com.example.trainingplanapp.featureMainScreen.presentation.common

import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.trainingplanapp.R

@Composable
fun TrainingItemCard(
    modifier: Modifier = Modifier,
    trainingName: String = "Siting",
    description: String = "Ultimately, the colors you choose will depend on your personal preferences and the context of your design. Consider the tone and message you want to convey, and experiment with different color combinations to find the one that works best for you.",
    numberOfExercises: String = "5",
    totalTime: String = "1 m",
    onClick: (String) -> Unit
) {
    Card(
        modifier = modifier
            .height(148.dp)
            .clickable {
                onClick(trainingName)
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
                    text = trainingName,
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.primary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.primary,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Text(
                        text = "$numberOfExercises repeat",
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.primary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = totalTime,
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.primary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}