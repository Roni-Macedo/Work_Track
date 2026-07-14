package com.example.worktrack.ui.presentation.screens.edit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.worktrack.domain.model.Work
import com.example.worktrack.ui.common.AppColors
import com.example.worktrack.ui.common.AppIcon
import com.example.worktrack.ui.common.CustomTextField
import com.example.worktrack.ui.presentation.components.DeleteADialog
import com.example.worktrack.ui.presentation.viewmodel.WorkViewModel

@Composable
fun EditScreen(
    id: Long,
    onBack: () -> Unit = {},
    viewModel: WorkViewModel = hiltViewModel()
) {
    val works by viewModel.works.collectAsState()
    val workToEdit = remember(id, works) { works.find { it.id.toLong() == id } }

    LaunchedEffect(workToEdit) {
        workToEdit?.let {
            viewModel.loadWorkToEdit(it)
        }
    }

    Scaffold(
        // Fundo azul na parte superior
        containerColor = MaterialTheme.colorScheme.primary,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, top = 48.dp, end = 24.dp, bottom = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { onBack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Voltar",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Editar registro",
                    style = MaterialTheme.typography.headlineSmall,
                    letterSpacing = 1.5.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    ) { paddingValues ->
        // Área branca com cantos arredondados no topo

        if (viewModel.showDeleteDialog) {
            DeleteADialog(
                onDismissRequest = { viewModel.onCloseDeleteDialog() },
                onConfirm = {
                    workToEdit?.let {
                        viewModel.deletar(it)
                        onBack()
                    }
                }
            )
        }

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .background(MaterialTheme.colorScheme.surface)
                .padding(24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Campo: Local
            Text(
                text = "Local",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleMedium,
                letterSpacing = 1.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            CustomTextField(
                value = viewModel.localInput,
                onValueChange = { viewModel.onLocalChange(it) },
                placeholder = "Ex.: Empresa, Casa, Mercado...",
                trailingIcon = Icons.Outlined.LocationOn,
                capitalization = KeyboardCapitalization.Words
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Campo: Dia da semana
            Text(
                text = "Dia da semana",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleMedium,
                letterSpacing = 1.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            CustomTextField(
                value = viewModel.diaSemanaInput,
                onValueChange = { viewModel.onDiaSemanaChange(it) },
                placeholder = "Selecione o dia",
                trailingIcon = Icons.Outlined.KeyboardArrowDown
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Campo: Data
            Text(
                text = "Data",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleMedium,
                letterSpacing = 1.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            CustomTextField(
                value = viewModel.dataInput,
                onValueChange = { viewModel.onDataChange(it) },
                placeholder = "",
                trailingIcon = Icons.Outlined.CalendarMonth
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Campo: Observações
            Text(
                text = "Observações (opcional)",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            CustomTextField(
                value = "",
                onValueChange = {  },
                placeholder = "Digite alguma observação...",
                modifier = Modifier.height(120.dp),
                singleLine = false,
                readOnly = true
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Botão: Salvar registro (Atualizar)
            Button(
                onClick = {
                    if (viewModel.localInput.isNotBlank()) {
                        viewModel.atualizar()
                        onBack()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    AppIcon(
                        imageVector = Icons.Outlined.CheckCircle,
                        color = AppColors.onPrimary(),
                        modifier = Modifier.size(24.dp),
                        onClick = {  }
                    )

                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Atualizar registro",
                        style = MaterialTheme.typography.titleMedium,
                        letterSpacing = 1.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botão: Apagar registro
            Button(
                onClick = {
                    viewModel.onOpenDeleteDialog()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppColors.error().copy(alpha = 0.6f)
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    AppIcon(
                        imageVector = Icons.Outlined.Delete,
                        color = AppColors.onPrimary(),
                        modifier = Modifier.size(24.dp),
                        onClick = {  }
                    )

                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Apagar registro",
                        style = MaterialTheme.typography.titleMedium,
                        letterSpacing = 1.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
