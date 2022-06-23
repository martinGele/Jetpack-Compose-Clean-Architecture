package com.demo.job.navigation

sealed class Screen(val route: String) {
    object Home: Screen(route = "home_screen")
}