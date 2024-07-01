package com.ammaryasser.tourating.util


sealed class Screen(val route: String) {

    data object Main : Screen("main_screen")

    data object Form : Screen("form_screen?id={id}") {
        const val ID = "id"

        fun editRoute(id: Int) = route.replace("{id}", id.toString())
    }

    data object Details : Screen("details_screen/{id}") {
        const val ID = "id"

        fun dynamicRoute(id: Int) = route.replace("{id}", id.toString())
    }

}