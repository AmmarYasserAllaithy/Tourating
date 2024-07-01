package com.ammaryasser.tourating.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.twotone.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue.EndToStart
import androidx.compose.material3.SwipeToDismissBoxValue.Settled
import androidx.compose.material3.SwipeToDismissBoxValue.StartToEnd
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ammaryasser.tourating.util.appRoundedShape


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListItemCard(
    siteName: String,
    rating: Int,
    review: String,
    onSwipeToStart: () -> Unit,
    onSwipeToEnd: () -> Unit,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = appRoundedShape,
        elevation = CardDefaults.cardElevation(2.dp),
        onClick = onClick
    ) {

        val dismissState = rememberSwipeToDismissBoxState(
            confirmValueChange = {
                if (it == StartToEnd) onSwipeToEnd()
                else if (it == EndToStart) onSwipeToStart()
                false
            }
        )

        SwipeToDismissBox(
            state = dismissState,
            modifier = Modifier.clip(appRoundedShape),
            backgroundContent = {
                val direction = dismissState.dismissDirection

                val color by animateColorAsState(
                    when (dismissState.targetValue) {
                        EndToStart -> Color.Red.copy(alpha = 0.8f)
                        StartToEnd -> MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f)
                        else -> MaterialTheme.colorScheme.background
                    }
                )

                val scale by animateFloatAsState(
                    if (dismissState.targetValue == Settled) 0f else 1.2f
                )

                val icon = when (direction) {
                    StartToEnd -> Icons.Outlined.Edit
                    EndToStart -> Icons.Outlined.Delete
                    else -> Icons.Outlined.Delete
                }

                val alignment = when (direction) {
                    StartToEnd -> Alignment.CenterStart
                    EndToStart -> Alignment.CenterEnd
                    else -> Alignment.Center
                }

                Box(
                    Modifier
                        .fillMaxSize()
                        .background(color)
                        .padding(horizontal = 16.dp)
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "",
                        modifier = Modifier
                            .scale(scale)
                            .align(alignment),
                        tint = Color.White
                    )
                }
            },
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(appRoundedShape)
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {

                RatingBar(rating = rating.toDouble(), starSize = 24.dp)

                Text(text = review, maxLines = 2)

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(imageVector = Icons.TwoTone.Place, contentDescription = null)

                    Text(text = siteName)

                }

            }
        }
    }
}