package com.gmail.redballtoy.sdweatherone.compose

import android.content.res.Resources.Theme
import android.graphics.ColorSpace
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import com.gmail.redballtoy.sdweatherone.R
import com.gmail.redballtoy.sdweatherone.ui.theme.BlueLight

@Composable
fun DialogSearch(
    dialogState: MutableState<Boolean>,
    onSubmit: (String) -> Unit
) {
    val dialogText = remember {
        mutableStateOf("")
    }

    AlertDialog(
        onDismissRequest = {
            dialogState.value = false
        },
        confirmButton = {
            TextButton(onClick = {
                onSubmit(dialogText.value)
                dialogState.value = false

            }) {
                Text(text = "Ok", color = Color.White, fontSize = TextUnit(24f, TextUnitType.Sp))
            }
        },
        dismissButton = {
            TextButton(onClick = {
                dialogState.value = false

            }) {
                Text(
                    text = "Cancel",
                    color = Color.White,
                    fontSize = TextUnit(24f, TextUnitType.Sp)
                )
            }
        },
        title = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "City or coordinate",
                    color = Color.White
                )
                TextField(value = dialogText.value, onValueChange = {
                    dialogText.value = searchText(it)
                })
            }
        },
        containerColor = Color(R.color.blue_light)
    )
}

fun searchText(it: String): String {
    return when (it) {
        "Myt" -> "55.899146,37.620878"
        "Mur" -> "57.062393,38.887889"
        else -> it
    }
}
