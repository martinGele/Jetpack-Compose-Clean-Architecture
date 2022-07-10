package com.demo.job.ui.compose.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.demo.presentation.model.Photos

@OptIn(ExperimentalCoilApi::class)
@Composable
fun HomeComponent(data: Photos, onItemClicked: (Int) -> Unit, modifier: Modifier = Modifier) {
    val typography = MaterialTheme.typography
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                bottom = 2.dp,
                top = 2.dp,
            )
            .fillMaxWidth()
            .clickable(onClick = {
                onItemClicked(data.id)
            }),
        elevation = 8.dp,
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Image(
                painter = rememberImagePainter(
                    data = data.thumbnailUrl,
                    builder = {
                        crossfade(true)
                    }
                ),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = modifier
                    .size(100.dp, 100.dp)
                    .clip(MaterialTheme.shapes.medium)
            )
            Column(
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .weight(1f)
            ) {
                Text(text = data.title, color = MaterialTheme.colors.primaryVariant, style = typography.h6)

            }
        }
    }
}