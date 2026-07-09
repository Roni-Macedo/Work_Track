package com.example.worktrack.ui.presentation.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.BusinessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.filled.WorkOutline
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.worktrack.ui.common.AppColors
import com.example.worktrack.ui.presentation.components.BottomAppBarItem
import com.example.worktrack.ui.presentation.viewmodel.WorkViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    onBack: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onAddClick: () -> Unit = {},
    onDashboardClick: () -> Unit = {},
    viewModel: WorkViewModel = hiltViewModel()
) {
    val localCounts by viewModel.localCounts.collectAsState()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.primary,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, top = 48.dp, end = 24.dp, bottom = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Voltar",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Gráfico",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        bottomBar = {
            Box(modifier = Modifier.background(AppColors.surface())) {
                NavigationBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                        .border(1.dp, AppColors.outlineVariant(), RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
                    containerColor = AppColors.surface(),
                    tonalElevation = 0.dp
                ) {
                    BottomAppBarItem(selected = false, onClick = onHomeClick, icon = Icons.Default.Home, label = "Início")
                    BottomAppBarItem(selected = false, onClick = onAddClick, icon = Icons.Default.Add, label = "Adicionar")
                    BottomAppBarItem(selected = true, onClick = onDashboardClick, icon = Icons.Default.BarChart, label = "Dashboard")
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .background(AppColors.surface())
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // Card do Gráfico
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Registros por local",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = Color(0xFF1F2937)
                        )
                        TextButton(onClick = { }) {
                            Text(text = "Ver todos", color = Color(0xFF1E88E5))
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    if (localCounts.isEmpty()) {
                        Text(
                            text = "Nenhum registro encontrado",
                            modifier = Modifier.fillMaxWidth().padding(vertical = 32.dp),
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                            color = Color.Gray
                        )
                    } else {
                        val maxCount = localCounts.maxOf { it.quantidade }.toFloat()

                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            itemsIndexed(localCounts) { index, item ->
                                ChartRow(
                                    local = item.local,
                                    count = item.quantidade,
                                    progress = item.quantidade / maxCount,
                                    color = getLocalColor(index, item.local)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ChartRow(local: String, count: Int, progress: Float, color: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(color.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = getLocalIcon(local),
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(18.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = local,
            modifier = Modifier.width(75.dp),
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
            color = Color(0xFF1F2937),
            maxLines = 1
        )

        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .weight(1f)
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp)),
            color = color,
            trackColor = Color(0xFFF3F4F6),
            strokeCap = androidx.compose.ui.graphics.StrokeCap.Round
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = count.toString(),
            modifier = Modifier.width(24.dp),
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            color = Color(0xFF1F2937),
            textAlign = androidx.compose.ui.text.style.TextAlign.End
        )
    }
}

fun getLocalColor(index: Int, local: String): Color {
    return when (local.lowercase()) {
        "empresa" -> Color(0xFF1E88E5) // Azul
        "casa" -> Color(0xFF42BB83)    // Verde
        "mercado" -> Color(0xFF7C4DFF)  // Roxo
        "oficina" -> Color(0xFFFFB300)  // Laranja
        else -> {
            val genericColors = listOf(
                Color(0xFF00BCD4), // Ciano
                Color(0xFFE91E63), // Rosa
                Color(0xFF8BC34A), // Limão
                Color(0xFFFF5722), // Laranja Escuro
                Color(0xFF607D8B)  // Cinza Azulado
            )
            genericColors[index % genericColors.size]
        }
    }
}

fun getLocalIcon(local: String): ImageVector {
    return when (local.lowercase()) {
        "empresa" -> Icons.Default.BusinessCenter
        "casa" -> Icons.Default.Home
        "mercado" -> Icons.Default.Storefront
        "oficina" -> Icons.Default.Work
        else -> Icons.Default.WorkOutline
    }
}
