package com.demo.job.ui.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.demo.job.navigation.NavGraphs

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppContent(navHostController: NavHostController) {
    val materialBlue700 = Color(0xFF4321D2)
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(title = {
                Text(
                    "Home",
                    color = Color.White,
                )
            }, backgroundColor = materialBlue700)
        },
        content = { NavGraphs(navHostController = navHostController) }
    )
}