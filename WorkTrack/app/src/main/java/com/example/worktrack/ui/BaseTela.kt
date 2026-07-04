package com.example.worktrack.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.worktrack.ui.presentation.screens.home.homecomponents.BottomAppBarItem
import com.example.worktrack.ui.theme.WorkTrackTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseTela() {

    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }

    // Aplicando o tema global para usar as cores e tipografia definidas
    WorkTrackTheme {
        Scaffold(
            // Fundo azul da parte superior usando a cor primária do tema
            containerColor = MaterialTheme.colorScheme.primary,
            topBar = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, top = 48.dp, end = 24.dp, bottom = 24.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Segunda-feira",
                                color = MaterialTheme.colorScheme.onPrimary,
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "26 de maio de 2026",
                                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        Row {

                            IconButton(onClick = { }) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "More",
                                    tint = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                        }
                    }
                }
            },
            bottomBar = {
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surface)
                ) {
                    NavigationBar(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.outlineVariant,
                                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                            ),
                        containerColor = MaterialTheme.colorScheme.surface,
                        tonalElevation = 0.dp
                    ) {
                        BottomAppBarItem(
                            selected = selectedItem == 0,
                            onClick = { selectedItem = 0 },
                            icon = Icons.Default.Home,
                            label = "Início"
                        )

                        BottomAppBarItem(
                            selected = selectedItem == 1,
                            onClick = {
                                selectedItem = 1
                            },
                            icon = Icons.Default.Add,
                            label = "Adicionar"
                        )

                        BottomAppBarItem(
                            selected = selectedItem == 2,
                            onClick = {
                                selectedItem = 2
                            },
                            icon = Icons.Default.BarChart,
                            label = "Dashboard"
                        )
                    }
                }
            },
//            floatingActionButton = {
//                FloatingActionButton(
//                    onClick = { },
//                    containerColor = MaterialTheme.colorScheme.primary,
//                    contentColor = MaterialTheme.colorScheme.onPrimary,
//                    shape = CircleShape
//                ) {
//                    Icon(Icons.Default.Add, contentDescription = "Add")
//                }
//            }
        ) { paddingValues ->
            // Container principal com fundo branco/claro e cantos arredondados no topo
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(horizontal = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(24.dp))

                // SEÇÃO: Resumo do dia (Estática, não rola com a lista)
                Text(
                    text = "Resumo do dia",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    OutlinedCard(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.outlinedCardColors(
                            containerColor = Color.Transparent
                        ),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                SummaryCard(
                                    title = "Total de registros",
                                    value = "125",
                                    icon = Icons.AutoMirrored.Outlined.Assignment,
                                    iconColor = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.weight(1f)
                                )
                                SummaryCard(
                                    title = "Locais únicos",
                                    value = "12",
                                    icon = Icons.Outlined.LocationOn,
                                    iconColor = Color(0xFF4CAF50),
                                    modifier = Modifier.weight(1f)
                                )
                            }
                            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                SummaryCard(
                                    title = "Registros hoje",
                                    value = "8",
                                    icon = Icons.Outlined.CalendarMonth,
                                    iconColor = Color(0xFF9C27B0),
                                    modifier = Modifier.weight(1f)
                                )
                                SummaryCard(
                                    title = "Este mês",
                                    value = "48",
                                    icon = Icons.AutoMirrored.Outlined.TrendingUp,
                                    iconColor = Color(0xFFFF9800),
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // SEÇÃO: Registros recentes (Título fixo acima da lista)
                Text(
                    text = "Registros recentes",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // LISTA DE REGISTROS (Apenas esta parte é rolável)
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(bottom = 24.dp)
                ) {
                    items(recentRecords) { record ->
                        RecordItem(record)
                    }
                }
            }
        }
    }
}

// Componente para os cards de resumo estatístico
@Composable
fun SummaryCard(
    title: String,
    value: String,
    icon: ImageVector,
    iconColor: Color,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = value,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(iconColor.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

// Componente para os itens da lista de registros recentes
@Composable
fun RecordItem(record: RecordData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Ícone circular com cor personalizada
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(record.color),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = record.icon,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = record.title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = record.dayOfWeek,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = record.date,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.outline,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

data class RecordData(
    val title: String,
    val dayOfWeek: String,
    val date: String,
    val icon: ImageVector,
    val color: Color
)

val recentRecords = listOf(
    RecordData(
        "Empresa",
        "Segunda-feira",
        "26/05/2026",
        Icons.Default.BusinessCenter,
        Color(0xFF4DB6AC)
    ),
    RecordData("Casa", "Segunda-feira", "26/05/2026", Icons.Default.Home, Color(0xFF7E57C2)),
    RecordData("Mercado", "Domingo", "25/05/2026", Icons.Default.ShoppingCart, Color(0xFFFFA726)),
    RecordData("Oficina", "Sábado", "24/05/2026", Icons.Default.Build, Color(0xFF1E88E5)),
    RecordData("Oficina", "Sábado", "24/05/2026", Icons.Default.Build, Color(0xFF1E88E5)),
    RecordData("Oficina", "Sábado", "24/05/2026", Icons.Default.Build, Color(0xFF1E88E5))
)

@Preview(showBackground = true)
@Composable
fun BaseTelaPreview() {
    BaseTela()
}
