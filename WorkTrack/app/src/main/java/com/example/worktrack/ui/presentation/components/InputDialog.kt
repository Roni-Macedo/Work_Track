package com.example.worktrack.ui.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

@Composable
fun InputDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: (String, String) -> Unit
) {

    var texto by remember {
        mutableStateOf("")
    }

    var dataTexto by remember {
        mutableStateOf(getWeekDay())
    }

    AlertDialog(

        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        ),

        onDismissRequest = { },


        title = {
            Text("Novo Dia de Trabalho")
        },

        text = {
            Column {
                OutlinedTextField(
                    value = dataTexto,
                    onValueChange = {
                        dataTexto = it
                    },
                    label = {
                        Text("Data")
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = texto,
                    onValueChange = {
                        texto = it
                    },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences
                    ),
                    label = {
                        Text("Local")
                    }
                )
            }
        },

        confirmButton = {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceEvenly,
            ) {
                TextButton(
                    onClick = {
                        onDismissRequest()
                    }
                ) {
                    Text("Cancelar")
                }
                TextButton(
                    onClick = {
                        if (texto.isNotBlank() && dataTexto.isNotBlank()) {
                            onConfirmation(texto, dataTexto)
                        }
                    }
                ) {
                    Text("Salvar")
                }
            }
        },
    )
}
