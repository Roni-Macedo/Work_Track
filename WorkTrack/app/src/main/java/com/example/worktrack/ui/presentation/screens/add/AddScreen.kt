package com.example.worktrack.ui.presentation.screens.add

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.worktrack.ui.common.CustomTextField
import com.example.worktrack.ui.presentation.components.getWeekDay
import com.example.worktrack.ui.presentation.viewmodel.WorkViewModel

@Composable
fun AddScreen(
    onBack: () -> Unit = {},
    viewModel: WorkViewModel = hiltViewModel()
) {
    val info = getWeekDay()

    LaunchedEffect(Unit) {
        if (viewModel.localInput.isEmpty()) {
            viewModel.onDiaSemanaChange(info.weekDay)
            viewModel.onDataChange(info.date)
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
                    text = "Novo registro",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    ) { paddingValues ->
        // Área branca com cantos arredondados no topo
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

            // Botão: Salvar registro
            Button(
                onClick = {
                    if (viewModel.localInput.isNotBlank()) {
                        viewModel.salvar()
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
                    Icon(
                        imageVector = Icons.Outlined.CheckCircle,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Salvar registro",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
