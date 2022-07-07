package com.demo.job.ui.compose.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.demo.presentation.viewmodel.DetailViewModel

@OptIn(ExperimentalCoilApi::class)
@Composable
fun DetailScreen(viewModel: DetailViewModel = hiltViewModel()) {

    val photos = viewModel.photos

    if (photos != null) {
        Column(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = rememberImagePainter(
                    data = photos.thumbnailUrl,
                    builder = {
                        crossfade(true)
                    }
                ),
                contentScale = ContentScale.FillBounds,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(MaterialTheme.shapes.medium)
            )

            Text(modifier = Modifier.padding(15.dp), text = photos.title, color = MaterialTheme.colors.primaryVariant, style = typography.h5)
        }
    } else {
        //TODO create composable that will
    }
}