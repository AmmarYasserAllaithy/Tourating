package com.ammaryasser.tourating.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ammaryasser.tourating.data.Tourating
import com.ammaryasser.tourating.ui.component.FAB
import com.ammaryasser.tourating.ui.component.TopBar
import com.ammaryasser.tourating.ui.component.TouratingCard
import com.ammaryasser.tourating.util.appGap
import com.ammaryasser.tourating.viewmodel.MainScreenViewModel


private lateinit var vm: MainScreenViewModel


@Composable
fun MainScreen(
    onAddTourating: () -> Unit,
    onEditTourating: (Int) -> Unit,
    onNavToDetailsScreen: (Int) -> Unit,
) {
    vm = viewModel(factory = MainScreenViewModel.Factory)

    Box(modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier.fillMaxSize()) {

            MainTopBar()

            vm.touratingList.observeAsState().value?.let {
                StaggeredGrid(
                    modifier = Modifier,
                    touratingList = it,
                    onItemEdit = onEditTourating,
                    onItemClick = onNavToDetailsScreen
                )
            }

        }

        FAB(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = onAddTourating
        )

    }
}

@Composable
fun MainTopBar() {
    TopBar {
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.TwoTone.Search,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Composable
fun StaggeredGrid(
    modifier: Modifier = Modifier,
    touratingList: List<Tourating>,
    onItemEdit: (Int) -> Unit,
    onItemClick: (Int) -> Unit
) {

    LazyVerticalStaggeredGrid(
        modifier = modifier,
        columns = StaggeredGridCells.Adaptive(minSize = 175.dp),
        contentPadding = PaddingValues(start = appGap, end = appGap, top = appGap, bottom = 88.dp),
        verticalItemSpacing = appGap,
        horizontalArrangement = Arrangement.spacedBy(space = appGap)
    ) {
        items(
            items = touratingList,
            key = { System.nanoTime() }
        ) {
            it.run {
                TouratingCard(
                    latitude,
                    longitude,
                    title,
                    rating,
                    onSwipeToStart = { /* todo: delete */ },
                    onSwipeToEnd = { onItemEdit(id) },
                    onClick = { onItemClick(id) }
                )
            }
        }
    }

}