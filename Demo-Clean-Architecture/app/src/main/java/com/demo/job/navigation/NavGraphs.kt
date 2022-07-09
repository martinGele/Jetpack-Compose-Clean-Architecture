package com.demo.job.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.demo.job.ui.compose.detail.DetailScreen
import com.demo.job.ui.compose.home.HomeScreen
import com.demo.presentation.model.NavTypePhotos
import com.demo.presentation.util.PHOTOS_BUNDLE_ITEM

@ExperimentalFoundationApi
@Composable
fun NavGraphs(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route
    ) {

        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(navController = navHostController)
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument(PHOTOS_BUNDLE_ITEM) {
                type = NavTypePhotos()
            })) {
            DetailScreen(hiltViewModel())
        }
    }
}