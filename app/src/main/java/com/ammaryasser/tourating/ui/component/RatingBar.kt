package com.ammaryasser.tourating.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.ceil
import kotlin.math.floor


/**
 * From: [Source](https://www.jetpackcompose.app/snippets/RatingBar)
 */
@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Double = 0.0,
    max: Int = 5,
    starSize: Dp = 32.dp,
    fillColor: Color = Color.Yellow,
    unFillColor: Color = Color.Gray,
) {

    val filledStars = floor(rating).toInt()
    val unfilledStars = (max - ceil(rating)).toInt()

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(filledStars) {
            Star(starSize, fillColor)
        }

        repeat(unfilledStars) {
            Star(starSize, unFillColor)
        }
    }

}


@Composable
fun Star(starSize: Dp, tint: Color) {
    Icon(
        imageVector = Icons.TwoTone.Star,
        contentDescription = null,
        tint = tint,
        modifier = Modifier.size(starSize)
    )
}


@Preview
@Composable
fun RatingPreview() {
    RatingBar(rating = 2.5)
}

@Preview
@Composable
fun TenStarsRatingPreview() {
    RatingBar(max = 10, rating = 8.5)
}

@Preview
@Composable
fun RatingPreviewFull() {
    RatingBar(rating = 5.0)
}

@Preview
@Composable
fun RatingPreviewWorst() {
    RatingBar(rating = 1.0)
}

@Preview
@Composable
fun RatingPreviewDisabled() {
    RatingBar(rating = 0.0, unFillColor = Color.Gray)
}