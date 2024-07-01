package com.ammaryasser.tourating.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ammaryasser.tourating.R
import com.ammaryasser.tourating.ui.component.RatingBar
import com.ammaryasser.tourating.ui.component.TopBar
import com.ammaryasser.tourating.util.appGap
import com.ammaryasser.tourating.util.appRoundedShape
import com.ammaryasser.tourating.util.formatTimestamp
import com.ammaryasser.tourating.viewmodel.DetailsScreenViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState


private lateinit var vm: DetailsScreenViewModel


@Composable
fun DetailsScreen(
    id: Int?,
    onNavBack: () -> Unit
) {
    vm = viewModel(factory = DetailsScreenViewModel.Factory)

    Column(modifier = Modifier.fillMaxSize()) {

        TopBar(
            title = stringResource(id = R.string.details),
            navBack = true,
            onNavBack = onNavBack
        )

        vm.tourating.observeAsState().value?.run {

            ReadonlyMap(latitude, longitude)


            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(appGap)
            ) {

                Text(
                    text = siteName,
                    style = typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Text(
                    text = createdAt.formatTimestamp(),
                    color = MaterialTheme.colorScheme.onBackground.copy(.5f)
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(appRoundedShape)
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(appGap),
                    verticalArrangement = Arrangement.spacedBy(appGap)
                ) {

                    RatingBar(rating = rating.toDouble())

                    Text(
                        text = review,
                        color = MaterialTheme.colorScheme.onBackground
                    )


                }


            }
        }

        id?.let { vm.fetchById(id) }

    }
}


@Composable
fun ReadonlyMap(
    latitude: Double,
    longitude: Double
) {
    val latLng = LatLng(latitude, longitude)

    GoogleMap(
        cameraPositionState = CameraPositionState(
            CameraPosition.fromLatLngZoom(latLng, 10f)
        ),
        uiSettings = MapUiSettings(
            zoomControlsEnabled = false
        ),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 333.dp)
    ) {
        Marker(state = MarkerState(position = latLng))
    }
}