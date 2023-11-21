package com.gmail.redballtoy.sdweatherone.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun DialogSearch(
    dialogState: MutableState<Boolean>,
    onSubmit: (String) -> Unit
) {
    val dialogText = remember {
        mutableStateOf("")
    }

    AlertDialog(onDismissRequest = {
        dialogState.value = false
    },
        confirmButton = {
            TextButton(onClick = {
                onSubmit(dialogText.value)
                dialogState.value = false

            }) {
                Text(text = "Ok")
            }
        },
        dismissButton = {
            TextButton(onClick = {
                dialogState.value = false

            }) {
                Text(text = "Cancel")
            }
        },
        title = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "City or coordinate")
                TextField(value = dialogText.value, onValueChange = {
                    dialogText.value = searchText(it)
                })
            }
        }
    )
}

fun searchText(it: String): String {
    return when (it) {
        "Myt" -> "55.899146,37.620878"
        "Mur" -> "57.062393,38.887889"
        else -> it
    }
}
