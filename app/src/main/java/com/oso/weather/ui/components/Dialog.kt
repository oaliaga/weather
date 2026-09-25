package com.oso.weather.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.oso.weather.R

@Composable
fun AntDialogInfo(info: String,
                  titleRes: Int,
                  confirmRes: Int = R.string.dialog_ok,
                  onDismissRequest: (Boolean) -> Unit) {
    AlertDialog(onDismissRequest = {onDismissRequest(false)},
        title = { Text(stringResource(titleRes)) },
        text = {Text(info)},
        confirmButton = {
            TextButton(onClick = {onDismissRequest(true)}) {
                Text(stringResource(confirmRes))
            }
        },
        dismissButton = {
            TextButton(onClick = {onDismissRequest(false)}) {
                Text(stringResource(R.string.dialog_cancel))
            }
        }
    )
}