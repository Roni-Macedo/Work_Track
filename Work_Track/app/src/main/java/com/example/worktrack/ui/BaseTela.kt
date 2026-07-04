package com.example.worktrack.ui

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.worktrack.ui.theme.WorkTrackTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseTela() {
    WorkTrackTheme {
        Scaffold(
            // Usando a cor primária do tema para o fundo do TopBar
            containerColor = MaterialTheme.colorScheme.primary,
            topBar = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 16.dp)
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
                                style = MaterialTheme.typography.headlineSmall, // Usando tipografia do tema
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
                                Icon(Icons.Default.Search, contentDescription = "Search", tint = MaterialTheme.colorScheme.onPrimary)
                            }
                            IconButton(onClick = { }) {
                                Icon(Icons.Default.MoreVert, contentDescription = "More", tint = MaterialTheme.colorScheme.onPrimary)
                            }
                        }
                    }
                }
            },
            bottomBar = {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    tonalElevation = 8.dp
                ) {
                    NavigationBarItem(
                        selected = true,
                        onClick = { },
                        icon = { Icon(Icons.Default.Home, contentDescription = "Início") },
                        label = { Text("Início", style = MaterialTheme.typography.labelMedium) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = Color.Transparent
                        )
                    )
                    NavigationBarItem(
                        selected = false,
                        onClick = { },
                        icon = { Icon(Icons.Default.BarChart, contentDescription = "Dashboard") },
                        label = { Text("Dashboard", style = MaterialTheme.typography.labelMedium) }
                    )
                }
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { },
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    shape = CircleShape
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                    .background(MaterialTheme.colorScheme.background) // Fundo do tema
                    .padding(horizontal = 16.dp)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(vertical = 24.dp)
                ) {
                    item {
                        Text(
                            text = "Resumo do dia",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        
                        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
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

                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            OutlinedTextField(
                                value = "",
                                onValueChange = {},
                                placeholder = { Text("Pesquisar local ou dia...", style = MaterialTheme.typography.bodyMedium) },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(56.dp),
                                shape = RoundedCornerShape(12.dp),
                                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                                    unfocusedBorderColor = Color.Transparent
                                )
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            IconButton(
                                onClick = { },
                                modifier = Modifier
                                    .size(56.dp)
                                    .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(12.dp))
                            ) {
                                Icon(Icons.Default.FilterList, contentDescription = "Filter")
                            }
                        }
                    }

                    item {
                        Text(
                            text = "Registros recentes",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                        )
                    }

                    items(recentRecords) { record ->
                        RecordItem(record)
                    }
                }
            }
        }
    }
}

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
                Icon(icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(24.dp))
            }
        }
    }
}

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
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(record.color),
                contentAlignment = Alignment.Center
            ) {
                Icon(record.icon, contentDescription = null, tint = Color.White, modifier = Modifier.size(24.dp))
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
                Icons.Default.ChevronRight,
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
    RecordData("Empresa", "Segunda-feira", "26/05/2026", Icons.Default.BusinessCenter, Color(0xFF4DB6AC)),
    RecordData("Casa", "Segunda-feira", "26/05/2026", Icons.Default.Home, Color(0xFF7E57C2)),
    RecordData("Mercado", "Domingo", "25/05/2026", Icons.Default.ShoppingCart, Color(0xFFFFA726)),
    RecordData("Oficina", "Sábado", "24/05/2026", Icons.Default.Build, Color(0xFF1E88E5))
)

@Preview(showBackground = true)
@Composable
fun BaseTelaPreview() {
    BaseTela()
}
