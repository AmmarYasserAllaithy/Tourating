package com.ammaryasser.tourating

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ammaryasser.tourating.ui.screen.DetailsScreen
import com.ammaryasser.tourating.ui.screen.MainScreen
import com.ammaryasser.tourating.util.Screen


@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Main.route
    ) {

        composable(route = Screen.Main.route) {
            MainScreen(
                onAddTourating = {
                    navController.navigate(Screen.Form.route)
                },
                onEditTourating = { id ->
                    navController.navigate(Screen.Form.editRoute(id))
                },
                onNavToDetailsScreen = { id ->
                    navController.navigate(Screen.Details.dynamicRoute(id))
                }
            )
        }

        composable(
            route = Screen.Details.route,
            arguments = listOf(
                navArgument(Screen.Details.ID) {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            DetailsScreen(id = it.arguments?.getInt(Screen.Details.ID)) {
                navController.popBackStack()
            }
        }
    }
}