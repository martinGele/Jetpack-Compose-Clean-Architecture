package com.demo.job.ui.compose.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.demo.presentation.viewmodel.DetailViewModel

@OptIn(ExperimentalCoilApi::class)
@Composable
fun DetailScreen(viewModel: DetailViewModel) {

    LaunchedEffect(key1 = viewModel) {
        val photos = viewModel.photos
        viewModel.getPhoto(photos?.id ?: 0)
    }

    val photos = viewModel.state.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = rememberImagePainter(
                data = photos.value.thumbnailUrl,
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

        Text(modifier = Modifier.padding(15.dp), text = photos.value.title, color = MaterialTheme.colors.primaryVariant, style = typography.h5)
    }

}