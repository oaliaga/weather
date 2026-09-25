package com.oso.weather.ui.components


import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.Timer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade

@Composable
fun AntCoilImage(url: String,
                 modifier: Modifier = Modifier,
                 shape: Shape = CircleShape) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        contentDescription = null,
        placeholder = rememberVectorPainter(Icons.Default.Timer),
        error = rememberVectorPainter(Icons.Default.BrokenImage),
        contentScale = ContentScale.Crop,
        modifier = modifier
            .clip(shape)
    )
}