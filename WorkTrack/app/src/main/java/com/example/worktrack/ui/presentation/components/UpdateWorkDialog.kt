package com.example.worktrack.ui.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.example.worktrack.domain.model.Work

@Composable
fun UpdateWorkDialog(
    work: Work,
    onDismiss: () -> Unit,
    onUpdate: (Work) -> Unit,
    onDelete: (Work) -> Unit
) {

    var texto by remember {
        mutableStateOf(work.local)
    }

    var dataTexto by remember {
        mutableStateOf(work.dayOfWeek)
    }

    AlertDialog(

        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        ),

        onDismissRequest = { },

        title = {
            Text("Atualizar")
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
                    label = {
                        Text("Local")
                    }
                )
            }
        },

        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { onDelete(work) }
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Excluir",
                        tint = Color.Red
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(onClick = { onDismiss() }) {
                        Text("Cancelar")
                    }
                    TextButton(
                        enabled = texto.isNotBlank() && dataTexto.isNotBlank(),
                        onClick = {
                            onUpdate(
                                work.copy(
                                    local = texto,
                                    dayOfWeek = dataTexto
                                )
                            )
                        }
                    ) {
                        Text("Salvar")
                    }
                }
            }
        },
        dismissButton = { }
    )
}
