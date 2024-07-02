package com.ammaryasser.tourating.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState


@Composable
fun GMap(
    modifier: Modifier = Modifier,
    latlng: LatLng = LatLng(0.0, 0.0),
    zoom: Float = 0f,
    onLatLngChange: (LatLng) -> Unit
) {

    var latLong by remember { mutableStateOf(latlng) }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(latLong, zoom)
    }

    GoogleMap(
        cameraPositionState = cameraPositionState,
        onMapClick = {
            latLong = it
            onLatLngChange(latLong)
        },
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        Marker(
            state = MarkerState(position = latLong),
            title = "Latitude, Longitude",
            snippet = "${latLong.latitude}, ${latLong.longitude}"
        )
    }

}