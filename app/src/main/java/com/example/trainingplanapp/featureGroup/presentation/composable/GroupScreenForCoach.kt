package com.example.trainingplanapp.featureGroup.presentation.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.trainingplanapp.featureGroup.domain.model.GroupInfo

@Composable
fun GroupScreenForCoach(
    modifier: Modifier = Modifier,
    onBecomeCoachButtonClick: () -> Unit,
    onAddGroupButtonClicked: () -> Unit,
    onAddTrainClick: (GroupInfo) -> Unit,
    onDeleteGroupClick: (GroupInfo) -> Unit,
    onEditGroupClick: (GroupInfo) -> Unit,
    groupInfoList: List<GroupInfo>,
    onConfirmRequestsButtonClick: () -> Unit,
    isCoach: Boolean = false
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
    ) {
        Spacer(modifier = Modifier.height(36.dp))
        if (!isCoach) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "You need to become a coach to see your subordinate groups",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.primary)
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        onClick = { onBecomeCoachButtonClick() },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
                    ) {
                        Text(
                            text = "Become a coach",
                            style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.background)
                        )
                    }
                }
            }
        } else {
            TextButton(
                onClick = { onConfirmRequestsButtonClick() },
                modifier = Modifier.align(Alignment.Start),
                contentPadding = PaddingValues(start = 0.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "Confirm user requests",
                        style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.primary),
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Your subordinate groups",
                    style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.primary)
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { onAddGroupButtonClicked() }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "add_group",
                        tint = MaterialTheme.colors.primary
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            groupInfoList.forEach { groupInfo ->
                GroupInfoItemForCoach(
                    groupInfo = groupInfo,
                    onDeleteButtonClick = { onDeleteGroupClick(it) },
                    onEditButtonClick = { onEditGroupClick(it) },
                    onAddTrainButtonClick = { onAddTrainClick(it)}
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}