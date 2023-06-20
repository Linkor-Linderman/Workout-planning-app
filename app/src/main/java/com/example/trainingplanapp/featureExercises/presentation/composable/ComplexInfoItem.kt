package com.example.trainingplanapp.featureExercises.presentation.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.trainingplanapp.R
import com.example.trainingplanapp.featureExercises.domain.model.ComplexInfo

@Composable
fun ComplexInfoItem(
    complexInfo: ComplexInfo,
    onCardClick: (ComplexInfo) -> Unit
) {
    Card(
        modifier = Modifier
            .height(148.dp)
            .clickable {
                onCardClick(complexInfo)
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
                    text = complexInfo.name,
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.primary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Type: ${complexInfo.complexType}",
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.primary,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Common: ${complexInfo.common}",
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.primary,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
