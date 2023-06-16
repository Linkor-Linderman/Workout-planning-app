package com.example.trainingplanapp.featureGroup.presentation.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.trainingplanapp.featureGroup.domain.model.RequestInfo

@Composable
fun RequestInfoItem(
    requestInfo: RequestInfo,
    onAcceptButtonClick: (RequestInfo) -> Unit,
    onDismissButtonClick: (RequestInfo) -> Unit
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
                        text = requestInfo.login,
                        style = MaterialTheme.typography.h6.copy(MaterialTheme.colors.primary),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = requestInfo.name,
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
                    IconButton(onClick = { onAcceptButtonClick(requestInfo) }) {
                        Image(
                            imageVector = Icons.Default.ThumbUp,
                            colorFilter = ColorFilter.tint(MaterialTheme.colors.primary),
                            contentDescription = "accept_request"
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    IconButton(onClick = { onDismissButtonClick(requestInfo) }) {
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