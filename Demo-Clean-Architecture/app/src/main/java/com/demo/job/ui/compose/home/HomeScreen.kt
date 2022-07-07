package com.demo.job.ui.compose.home

import android.net.Uri
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.demo.job.navigation.Screen
import com.demo.job.ui.util.CircularProgressBar
import com.demo.job.ui.util.ConnectivityStatus
import com.demo.job.ui.util.ErrorComponent
import com.demo.job.ui.util.connectivityState
import com.demo.presentation.viewmodel.HomeViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.gson.Gson

@OptIn(ExperimentalAnimationApi::class, kotlinx.coroutines.ExperimentalCoroutinesApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val result = viewModel.state.collectAsState()
    val status = viewModel.loadingState.collectAsState()

    val state by connectivityState()
    SwipeRefresh(
        state = rememberSwipeRefreshState(status.value),
        onRefresh = { viewModel.refresh() },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            CircularProgressBar(
                isDisplayed = status.value,
                modifier = Modifier.testTag("progress_bar")
            )
            ErrorComponent()
            val listState = rememberLazyListState()
            LazyColumn(state = listState,
                modifier = Modifier.testTag("demo-list-testTag")) {
                items(
                    items = result.value,
                    itemContent = { item ->
                        HomeComponent(
                            data = item,
                            onItemClicked = {
                                val photosItem = Uri.encode(Gson().toJson(it))
                                navController.navigate(Screen.Detail.createRoute(photosItem))
                            }
                        )
                    }
                )
            }
            ConnectivityStatus(connection = state, onRefresh = { viewModel.refresh() })
        }
    }
}