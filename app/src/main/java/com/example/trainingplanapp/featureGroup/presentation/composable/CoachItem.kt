package com.example.trainingplanapp.featureGroup.presentation.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.trainingplanapp.featureGroup.domain.model.CoachInfo

@Composable
fun CoachInfoItem(
    coachInfo: CoachInfo,
    onCardClick: (CoachInfo) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .clickable {
                onCardClick(coachInfo)
            },
        backgroundColor = MaterialTheme.colors.secondary,
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier.weight(0.5f),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = coachInfo.shortName,
                    style = MaterialTheme.typography.h6.copy(MaterialTheme.colors.primary),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center,
            ){
                Text(
                    text = "Coach name: " + coachInfo.name,
                    style = MaterialTheme.typography.body1.copy(MaterialTheme.colors.primary)
                )
            }
        }
    }
}