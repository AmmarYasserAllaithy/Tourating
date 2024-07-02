package com.ammaryasser.tourating.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ammaryasser.tourating.data.Tourating
import com.ammaryasser.tourating.ui.component.ConfirmationDialog
import com.ammaryasser.tourating.ui.component.FAB
import com.ammaryasser.tourating.ui.component.ListItemCard
import com.ammaryasser.tourating.ui.component.TopBar
import com.ammaryasser.tourating.util.appGap
import com.ammaryasser.tourating.viewmodel.MainScreenViewModel


private lateinit var vm: MainScreenViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onAddTourating: () -> Unit,
    onEditTourating: (Int) -> Unit,
    onNavToDetailsScreen: (Int) -> Unit,
) {
    vm = viewModel(factory = MainScreenViewModel.Factory)

    Box(modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier.fillMaxSize()) {

            var showSearchField by remember { mutableStateOf(false) }
            var query by remember { mutableStateOf("") }

            MainTopBar { showSearchField = !showSearchField }

            if (showSearchField)
                SearchBar(
                    modifier = Modifier
                        .padding(appGap)
                        .fillMaxWidth(),
                    leadingIcon = {
                        Icon(Icons.TwoTone.Search, null)
                    },
                    query = query,
                    onQueryChange = { query = it },
                    onSearch = { /* TODO */ },
                    active = false,
                    onActiveChange = {}
                ) {

                }

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
fun MainTopBar(onClickSearch: () -> Unit) {
    TopBar {
        IconButton(onClick = onClickSearch) {
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

    var showDialog by remember { mutableStateOf(false) }
    var deletionId by remember { mutableIntStateOf(-1) }

    LazyVerticalStaggeredGrid(
        modifier = modifier,
        columns = StaggeredGridCells.Adaptive(minSize = 222.dp),
        contentPadding = PaddingValues(start = appGap, end = appGap, top = appGap, bottom = 88.dp),
        verticalItemSpacing = appGap,
        horizontalArrangement = Arrangement.spacedBy(space = appGap)
    ) {
        items(
            items = touratingList,
            key = { System.nanoTime() }
        ) {
            it.run {
                ListItemCard(
                    siteName = siteName,
                    rating = rating,
                    review = review,
                    onSwipeToStart = {
                        showDialog = true
                        deletionId = id
                    },
                    onSwipeToEnd = { onItemEdit(id) },
                    onClick = { onItemClick(id) }
                )
            }
        }
    }

    if (showDialog)
        ConfirmationDialog(
            title = "Delete tourating",
            text = "Do you want to permanently delete the review?",
            confirmText = "Yes, delete",
            dismissText = "No, keep",
            onDismiss = { showDialog = false }
        ) {
            vm.delete(deletionId)
            showDialog = false
        }

}