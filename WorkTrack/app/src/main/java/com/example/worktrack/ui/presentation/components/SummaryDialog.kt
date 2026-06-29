package com.example.worktrack.ui.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.worktrack.domain.model.Work

@Composable
fun SearchDialog(
    works: List<Work>,
    onDismissRequest: () -> Unit
) {
    val contagem = works.groupingBy { it.local }.eachCount()

    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text("Resumo de Atividades")
        },
        text = {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    contagem.forEach { (local, quantidade) ->
                        Text("$local: $quantidade")
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismissRequest) {
                Text("Fechar")
            }
        }
    )
}
