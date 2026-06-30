package com.example.worktrack.ui.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.worktrack.ui.presentation.components.InputDialog
import com.example.worktrack.ui.presentation.components.SearchDialog
import com.example.worktrack.ui.presentation.components.UpdateWorkDialog
import com.example.worktrack.ui.presentation.components.getWeekDay
import com.example.worktrack.ui.presentation.viewmodel.WorkViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkScreen(
    viewModel: WorkViewModel = hiltViewModel()
) {

    val lista by viewModel.works.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(getWeekDay())
                },
                actions = {
                    IconButton(onClick = { viewModel.onOpenMenu() }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Menu")
                    }
                    DropdownMenu(
                        expanded = viewModel.showMenu,
                        onDismissRequest = { viewModel.onCloseMenu() }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Resumo", fontSize = 20.sp) },
                            onClick = {
                                viewModel.onOpenSearchDialog()
                                viewModel.onCloseMenu()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Excluir tudo", fontSize = 20.sp, color = Color.Red) },
                            onClick = {
                                viewModel.onOpenDeleteAllConfirmation()
                                viewModel.onCloseMenu()
                            }
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.onOpenDialog() }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { inner ->
        if (viewModel.showSearchDialog) {
            SearchDialog(
                works = lista,
                onDismissRequest = { viewModel.onCloseSearchDialog() }
            )
        }

        if (viewModel.showDeleteAllConfirmation) {
            AlertDialog(
                onDismissRequest = { viewModel.onCloseDeleteAllConfirmation() },
                title = { Text("Confirmar Exclusão") },
                text = { Text("Tem certeza que deseja excluir todos os registros? Esta ação não pode ser desfeita.") },
                confirmButton = {
                    TextButton(
                        onClick = { viewModel.deletarTudo() }
                    ) {
                        Text("Excluir Tudo", color = Color.Red)
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = { viewModel.onCloseDeleteAllConfirmation() }
                    ) {
                        Text("Cancelar")
                    }
                }
            )
        }

        Column(
            modifier = Modifier.padding(inner),
        ) {
            if (viewModel.showDialog) {
                InputDialog(
                    onDismissRequest = {
                        viewModel.onCloseDialog()
                    },
                    onConfirmation = { texto, data ->
                        viewModel.salvar(texto, data)
                    }
                )
            }

            if (viewModel.showUpdateDialog && viewModel.selectedWork != null) {
                UpdateWorkDialog(
                    work = viewModel.selectedWork!!,
                    onDismiss = {
                        viewModel.onCloseUpdateDialog()
                    },
                    onUpdate = { workAtualizado ->
                        viewModel.atualizar(workAtualizado)
                    },
                    onDelete = { workParaDeletar ->
                        viewModel.deletar(workParaDeletar)
                    }
                )
            }


            LazyColumn(
                reverseLayout = true,
            ) {
                items(lista) { item ->
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        thickness = 1.dp,
                        color = Color.LightGray
                    )
                    Row(
                        Modifier
                            .padding(12.dp)
                            .clickable {
                                viewModel.onOpenUpdateDialog(item)
                            },
                    ) {
                        Text(item.dayOfWeek)
                        Text(" | ${item.local}")
                    }
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        thickness = 1.dp,
                        color = Color.LightGray
                    )

                }
            }
        }
    }
}
