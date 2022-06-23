package com.demo.job.ui.compose.detail

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.demo.presentation.viewmodel.DetailViewModel

@Composable
fun DetailScreen(viewModel: DetailViewModel = hiltViewModel()) {


    val getArgs = viewModel.photos
    Log.d("getArgs", getArgs.toString())
}