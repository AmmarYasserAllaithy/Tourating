package com.ammaryasser.tourating.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Place
import androidx.compose.material.icons.twotone.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ammaryasser.tourating.R
import com.ammaryasser.tourating.data.Tourating
import com.ammaryasser.tourating.ui.component.FormTextField
import com.ammaryasser.tourating.ui.component.GMap
import com.ammaryasser.tourating.ui.component.TopBar
import com.ammaryasser.tourating.ui.icon.Review
import com.ammaryasser.tourating.util.appGap
import com.ammaryasser.tourating.viewmodel.FormScreenViewModel
import com.google.android.gms.maps.model.LatLng


private lateinit var vm: FormScreenViewModel


@Composable
fun FormScreen(
    id: Int?,
    onNavBack: () -> Unit
) {
    vm = viewModel(factory = FormScreenViewModel.Factory)

    var isEditMode by remember { mutableStateOf(false) }
    var latitude by remember { mutableDoubleStateOf(0.0) }
    var longitude by remember { mutableDoubleStateOf(0.0) }
    var siteName by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf("") }
    var review by remember { mutableStateOf("") }
    var renderMap by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        id?.takeIf { it > -1 }?.let {
            isEditMode = true

            vm.fetchById(it).collect { tourating ->
                latitude = tourating.latitude
                longitude = tourating.longitude
                siteName = tourating.siteName
                rating = tourating.rating.toString()
                review = tourating.review
                renderMap = true
            }
        } ?: run { renderMap = true }
    }

    BoxWithConstraints(Modifier.fillMaxSize()) {

        ConstraintLayout(
            constraintSet = OrientationConstraints(minWidth),
            modifier = Modifier.matchParentSize()
        ) {

            TopBar(
                modifier = Modifier.layoutId(Layouts.TopBar.id()),
                title = stringResource(if (isEditMode) R.string.edit_tourating else R.string.add_tourating),
                navBack = true,
                onNavBack = onNavBack
            )

            if (renderMap)
                GMap(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .layoutId(Layouts.GMap.id()),
                    latlng = LatLng(latitude, longitude)
                ) {
                    latitude = it.latitude
                    longitude = it.longitude
                }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .layoutId(Layouts.FormColumn.id()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                FormTextField(
                    icon = Icons.TwoTone.Place,
                    labelResId = R.string.site_name_label,
                    placeholderResId = R.string.site_name_placeholder,
                    value = siteName
                ) {
                    siteName = it
                }

                FormTextField(
                    icon = Icons.TwoTone.Star,
                    labelResId = R.string.rating_label,
                    placeholderResId = R.string.rating_placeholder,
                    value = rating,
                    isNumber = true
                ) {
                    rating = it
                }

                FormTextField(
                    icon = Review(),
                    labelResId = R.string.review_label,
                    placeholderResId = R.string.review_placeholder,
                    value = review,
                    isMultiLine = true
                ) {
                    review = it
                }

                Button(
                    modifier = Modifier
                        .padding(appGap)
                        .fillMaxWidth(),
                    onClick = {
                        val tourating = Tourating(
                            latitude = latitude,
                            longitude = longitude,
                            siteName = siteName,
                            rating = rating.toInt(),
                            review = review
                        )

                        if (isEditMode) id?.let { tourating.id = it }

                        vm.insertOrUpdate(tourating)
                        onNavBack()
                    }
                ) {
                    Text(text = "Done")
                }

            }
        }

    }
}
