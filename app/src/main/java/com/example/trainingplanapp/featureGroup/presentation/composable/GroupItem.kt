package com.example.trainingplanapp.featureGroup.presentation.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.trainingplanapp.featureGroup.domain.model.GroupInfo

@Composable
fun GroupInfoItem(
    groupInfo: GroupInfo,
    onCardClick: (GroupInfo) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .clickable {
                onCardClick(groupInfo)
            },
        backgroundColor = MaterialTheme.colors.secondary,
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = groupInfo.name,
                style = MaterialTheme.typography.h6.copy(MaterialTheme.colors.primary),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "Person count: " + groupInfo.personCount,
                style = MaterialTheme.typography.body1.copy(MaterialTheme.colors.primary)
            )
        }
    }
}

@Composable
fun GroupInfoItemWithCoachName(
    groupInfo: GroupInfo,
    onCardClick: (GroupInfo) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .clickable {
                onCardClick(groupInfo)
            },
        backgroundColor = MaterialTheme.colors.secondary,
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = groupInfo.name,
                style = MaterialTheme.typography.h6.copy(MaterialTheme.colors.primary),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Column() {
                Text(
                    text = "Coach name: " + groupInfo.mainTrainerName,
                    style = MaterialTheme.typography.body1.copy(MaterialTheme.colors.primary)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Person count: " + groupInfo.personCount,
                    style = MaterialTheme.typography.body1.copy(MaterialTheme.colors.primary)
                )
            }
        }
    }
}

@Composable
fun GroupInfoItemForCoach(
    groupInfo: GroupInfo,
    onEditButtonClick: (GroupInfo) -> Unit,
    onDeleteButtonClick: (GroupInfo) -> Unit,
    onAddTrainButtonClick: (GroupInfo) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp),
        backgroundColor = MaterialTheme.colors.secondary,
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = groupInfo.name,
                    style = MaterialTheme.typography.h6.copy(MaterialTheme.colors.primary),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "Person count: " + groupInfo.personCount,
                        style = MaterialTheme.typography.body1.copy(MaterialTheme.colors.primary)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        modifier = Modifier.clickable {
                            onEditButtonClick(groupInfo)
                        },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Edit",
                            style = MaterialTheme.typography.body2.copy(MaterialTheme.colors.primary)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            modifier = Modifier.size(14.dp),
                            imageVector = Icons.Default.Edit,
                            contentDescription = "edit_icon",
                            tint = MaterialTheme.colors.primary
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        modifier = Modifier
                            .clickable {
                                onDeleteButtonClick(groupInfo)
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = "Delete",
                            style = MaterialTheme.typography.body2.copy(MaterialTheme.colors.primary)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            modifier = Modifier.size(14.dp),
                            imageVector = Icons.Default.Clear,
                            contentDescription = "edit_icon",
                            tint = MaterialTheme.colors.primary
                        )
                    }
                    Row(
                        modifier = Modifier
                            .clickable {
                                onAddTrainButtonClick(groupInfo)
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = "Add train",
                            style = MaterialTheme.typography.body2.copy(MaterialTheme.colors.primary)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            modifier = Modifier.size(14.dp),
                            imageVector = Icons.Default.Add,
                            contentDescription = "edit_icon",
                            tint = MaterialTheme.colors.primary
                        )
                    }
                }
            }
        }
    }
}