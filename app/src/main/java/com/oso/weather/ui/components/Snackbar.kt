package com.oso.weather.ui.components

import android.graphics.Paint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.oso.weather.R
import com.oso.weather.common.utils.Constants
import com.oso.weather.ui.theme.CommonPaddingDefault
import com.oso.weather.ui.theme.CommonPaddingMin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AntSnackbar(
    modifier: Modifier = Modifier,
    msgRes: Int,
    colorBackground: Color = Color.Transparent,
    shape: Shape = RectangleShape,
    isPreview: Boolean = false,
    onDismiss: () -> Unit
) {
    val scope = rememberCoroutineScope()
    var showSnackbar by remember { mutableStateOf(isPreview) }
    var msg = stringResource(id = msgRes)
    LaunchedEffect(msg) {
        scope.launch {
            showSnackbar = true
            delay(Constants.DURATION_SHORT)
            onDismiss()
            showSnackbar = false
        }
    }
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center) {
        AnimatedVisibility(visible = msg.isNotBlank() && showSnackbar) {
            Text(
                text = msg,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .background(
                        color = colorBackground, shape = shape
                    )
                    .padding(
                        vertical = CommonPaddingMin,
                        horizontal = CommonPaddingDefault
                    )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AntSnackbarPreview() {
    MaterialTheme {
        AntSnackbar(
            msgRes = R.string.app_name,
            isPreview = true,
            colorBackground = Color.Yellow,
            shape = CircleShape

        ) {

        }
    }
}