package com.example.trainingplanapp.featureGroup.presentation.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.trainingplanapp.featureGroup.domain.model.GroupInfo

@Composable
fun GroupScreenForUser(
    modifier: Modifier = Modifier,
    onJoinCoachButtonClicked: () -> Unit,
    groupInfoList: List<GroupInfo>,
    onCardClick: (GroupInfo) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(36.dp))
        Button(
            onClick = { onJoinCoachButtonClicked() },
            modifier = Modifier
                .fillMaxWidth(),
            elevation = null,
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.primary
            )
        ) {
            Text(
                text = "Join a coach",
                style = MaterialTheme.typography.h5.copy(
                    color = MaterialTheme.colors.background
                )
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Your groups:",
            style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.primary)
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (groupInfoList.isEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterHorizontally),
                text = "You are not a member of the group yet",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6.copy(
                    color = MaterialTheme.colors.primary.copy(
                        alpha = 0.8f
                    )
                ),
            )
        } else {
            groupInfoList.forEach { groupInfo ->
                GroupInfoItemWithCoachName(groupInfo = groupInfo, onCardClick = { onCardClick(it) })
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}