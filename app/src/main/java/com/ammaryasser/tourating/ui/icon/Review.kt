package com.ammaryasser.tourating.ui.icon

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp


@Composable
fun Review(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "reviews",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 40.0f,
            viewportHeight = 40.0f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(19.375f, 22.958f)
                quadToRelative(0.208f, 0.417f, 0.625f, 0.417f)
                quadToRelative(0.417f, 0f, 0.625f, -0.417f)
                lineTo(22.375f, 19f)
                lineToRelative(3.917f, -1.75f)
                quadToRelative(0.416f, -0.167f, 0.416f, -0.604f)
                quadToRelative(0f, -0.438f, -0.416f, -0.604f)
                lineToRelative(-3.917f, -1.75f)
                lineToRelative(-1.75f, -3.959f)
                quadToRelative(-0.208f, -0.416f, -0.625f, -0.416f)
                quadToRelative(-0.417f, 0f, -0.625f, 0.416f)
                lineToRelative(-1.75f, 3.959f)
                lineToRelative(-3.917f, 1.75f)
                quadToRelative(-0.416f, 0.166f, -0.416f, 0.604f)
                quadToRelative(0f, 0.437f, 0.416f, 0.604f)
                lineTo(17.625f, 19f)
                close()
                moveTo(3.625f, 33.125f)
                verticalLineTo(6.208f)
                quadToRelative(0f, -1.041f, 0.771f, -1.833f)
                reflectiveQuadToRelative(1.854f, -0.792f)
                horizontalLineToRelative(27.5f)
                quadToRelative(1.042f, 0f, 1.833f, 0.792f)
                quadToRelative(0.792f, 0.792f, 0.792f, 1.833f)
                verticalLineToRelative(20.917f)
                quadToRelative(0f, 1.042f, -0.792f, 1.833f)
                quadToRelative(-0.791f, 0.792f, -1.833f, 0.792f)
                horizontalLineTo(10.125f)
                lineToRelative(-4.292f, 4.292f)
                quadToRelative(-0.625f, 0.625f, -1.416f, 0.27f)
                quadToRelative(-0.792f, -0.354f, -0.792f, -1.187f)
                close()
                moveToRelative(2.625f, -3.25f)
                lineTo(9f, 27.125f)
                horizontalLineToRelative(24.75f)
                verticalLineTo(6.208f)
                horizontalLineTo(6.25f)
                close()
                moveToRelative(0f, -23.667f)
                verticalLineToRelative(23.667f)
                close()
            }
        }.build()
    }
}