package com.ammaryasser.tourating.ui.screen

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.ammaryasser.tourating.util.appGap


enum class Layouts {
    TopBar,
    GMap,
    FormColumn;

    fun id() = "$name-layout-id"
}


@SuppressLint("ComposableNaming")
@Composable
fun OrientationConstraints(minWidth: Dp) =
    if (minWidth < 600.dp) PortraitConstraints(margin = appGap)
    else LandscapeConstraints(margin = appGap)


@SuppressLint("ComposableNaming")
@Composable
private fun PortraitConstraints(margin: Dp) = ConstraintSet {
    val topBar = createRefFor(Layouts.TopBar.id())
    val gMap = createRefFor(Layouts.GMap.id())
    val formColumn = createRefFor(Layouts.FormColumn.id())

    val startGuideline = createGuidelineFromStart(margin)
    val endGuideline = createGuidelineFromEnd(margin)

    constrain(topBar) {
        top.linkTo(parent.top)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
    }

    constrain(gMap) {
        top.linkTo(topBar.bottom, margin = margin)
        start.linkTo(startGuideline)
        end.linkTo(endGuideline)
        width = Dimension.fillToConstraints
    }

    constrain(formColumn) {
        top.linkTo(gMap.bottom, margin = margin)
        start.linkTo(gMap.start)
        end.linkTo(gMap.end)
        width = Dimension.fillToConstraints
    }
}


@SuppressLint("ComposableNaming")
@Composable
private fun LandscapeConstraints(margin: Dp) = ConstraintSet {
    val topBar = createRefFor(Layouts.TopBar.id())
    val gMap = createRefFor(Layouts.GMap.id())
    val formColumn = createRefFor(Layouts.FormColumn.id())

    constrain(topBar) {
        top.linkTo(parent.top)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
    }

    constrain(gMap) {
        top.linkTo(topBar.bottom, margin = margin)
        bottom.linkTo(parent.bottom, margin = margin)
        start.linkTo(parent.start, margin = margin)
        end.linkTo(formColumn.start, margin = margin)

        width = Dimension.fillToConstraints
        height = Dimension.fillToConstraints
    }

    constrain(formColumn) {
        top.linkTo(gMap.top)
        bottom.linkTo(gMap.bottom)
        start.linkTo(gMap.end)
        end.linkTo(parent.end, margin = margin)

        width = Dimension.fillToConstraints
        height = Dimension.fillToConstraints
    }
}