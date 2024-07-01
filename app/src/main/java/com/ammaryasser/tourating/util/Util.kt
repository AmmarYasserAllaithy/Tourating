package com.ammaryasser.tourating.util

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date


val appGap = 16.dp
val appRoundedShape = RoundedCornerShape(16.dp)


fun Long.formatTimestamp(): String = SimpleDateFormat("MMM dd, yyyy").format(Date(this * 1000))
