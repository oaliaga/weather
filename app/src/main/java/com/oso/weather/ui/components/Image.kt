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
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.disk.DiskCache
import coil3.disk.directory
import coil3.memory.MemoryCache
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.crossfade
import org.koin.compose.koinInject

@Composable
fun AntCoilImage(
    url: String,
    modifier: Modifier = Modifier,
    shape: Shape = CircleShape,
    imageLoader: ImageLoader = koinInject()
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .diskCachePolicy(CachePolicy.ENABLED)
            .memoryCacheKey(url)
            .diskCacheKey(url)
            .build(),
        imageLoader = imageLoader,
        contentDescription = null,
        placeholder = rememberVectorPainter(Icons.Default.Timer),
        error = rememberVectorPainter(Icons.Default.BrokenImage),
        contentScale = ContentScale.Crop,
        modifier = modifier
            .clip(shape)
    )
}