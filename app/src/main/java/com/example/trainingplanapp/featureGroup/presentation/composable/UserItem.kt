package com.example.trainingplanapp.featureGroup.presentation.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.trainingplanapp.featureGroup.domain.model.UserInfo

@Composable
fun UserInfoItem(
    userInfo: UserInfo,
    deleteFromList: (UserInfo) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        backgroundColor = MaterialTheme.colors.secondary,
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = Modifier.weight(0.5f),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Text(
                        text = userInfo.login,
                        style = MaterialTheme.typography.h6.copy(MaterialTheme.colors.primary),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = userInfo.name,
                        style = MaterialTheme.typography.subtitle1.copy(MaterialTheme.colors.primary),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterEnd
            ) {
                Row(
                    modifier = Modifier.padding(end = 16.dp)
                ) {
                    IconButton(onClick = { deleteFromList(userInfo) }) {
                        Image(
                            imageVector = Icons.Default.Clear,
                            colorFilter = ColorFilter.tint(MaterialTheme.colors.primary),
                            contentDescription = "accept_request"
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun UserInfoWithoutButton(
    userInfo: UserInfo,
    chooseUser: (UserInfo) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                chooseUser(userInfo)
            }
            .height(64.dp),
        backgroundColor = MaterialTheme.colors.secondary,
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = Modifier.weight(0.5f),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Text(
                        text = userInfo.login,
                        style = MaterialTheme.typography.h6.copy(MaterialTheme.colors.primary),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = userInfo.name,
                        style = MaterialTheme.typography.subtitle1.copy(MaterialTheme.colors.primary),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterEnd
            ) {
                Row(
                    modifier = Modifier.padding(end = 16.dp)
                ) {
                    Image(
                        imageVector = Icons.Default.Person,
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.primary),
                        contentDescription = "accept_request"
                    )
                }
            }
        }
    }
}