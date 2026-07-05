package com.example.worktrack.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.worktrack.ui.theme.WorkTrackTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseTela() {
    WorkTrackTheme {
        Scaffold(
            // Fundo azul na parte superior
            containerColor = MaterialTheme.colorScheme.primary,
            topBar = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 48.dp, end = 16.dp, bottom = 24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { /* Voltar */ }) {
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
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                CustomTextField(
                    value = "",
                    placeholder = "Ex.: Empresa, Casa, Mercado...",
                    trailingIcon = Icons.Outlined.LocationOn
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Campo: Dia da semana
                Text(
                    text = "Dia da semana",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                CustomTextField(
                    value = "",
                    placeholder = "Selecione o dia",
                    trailingIcon = Icons.Outlined.KeyboardArrowDown
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Campo: Data
                Text(
                    text = "Data",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                CustomTextField(
                    value = "26/05/2026",
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
                    placeholder = "Digite alguma observação...",
                    modifier = Modifier.height(120.dp),
                    singleLine = false
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Botão: Salvar registro
                Button(
                    onClick = { /* Salvar */ },
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
}

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit = {},
    placeholder: String,
    trailingIcon: ImageVector? = null,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    readOnly: Boolean = false,
    capitalization: KeyboardCapitalization = KeyboardCapitalization.None
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        trailingIcon = {
            if (trailingIcon != null) {
                Icon(
                    imageVector = trailingIcon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                )
            }
        },
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
            focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
        ),
        singleLine = singleLine,
        readOnly = readOnly,
        keyboardOptions = KeyboardOptions(
            capitalization = capitalization
        ),
        textStyle = MaterialTheme.typography.bodyLarge
    )
}

@Preview(showBackground = true)
@Composable
fun BaseTelaPreview() {
    BaseTela()
}
