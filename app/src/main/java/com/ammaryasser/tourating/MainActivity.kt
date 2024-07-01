package com.ammaryasser.tourating

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.ammaryasser.tourating.ui.theme.TouratingTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // todo: require all permissions

        enableEdgeToEdge()

        setContent {
            TouratingTheme {
                NavGraph(navController = rememberNavController())
            }
        }
    }
}
