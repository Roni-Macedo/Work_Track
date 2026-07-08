package com.example.worktrack.ui.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Assignment
import androidx.compose.material.icons.automirrored.outlined.TrendingUp
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.worktrack.ui.common.AppColors
import com.example.worktrack.ui.presentation.components.getWeekDay
import com.example.worktrack.ui.presentation.components.BottomAppBarItem
import com.example.worktrack.ui.presentation.viewmodel.WorkViewModel
import com.example.worktrack.ui.presentation.components.CardListItem
import com.example.worktrack.ui.presentation.components.NotesCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onAddClick: () -> Unit = {},
    onDashboardClick: () -> Unit = {},
    onEditClick: (Long) -> Unit = {},
    onNotesClick: () -> Unit = {},
    viewModel: WorkViewModel = hiltViewModel()
) {

    val lista by viewModel.works.collectAsState()

    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }

    Scaffold(
        containerColor = AppColors.primary(),
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
                            text = getWeekDay().weekDay,
                            color = AppColors.onPrimary(),
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = getWeekDay().date,
                            color = AppColors.onPrimary().copy(alpha = 0.8f),
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                    Row {
                        IconButton(onClick = { viewModel.onOpenMenu() }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu",
                                tint = AppColors.onPrimary(),
                            )
                            DropdownMenu(
                                expanded = viewModel.showMenu,
                                onDismissRequest = { viewModel.onCloseMenu() }
                            ) {
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            "Excluir tudo",
                                            fontSize = 20.sp,
                                            color = Color.Red
                                        )
                                    },
                                    onClick = {
//                                        viewModel.onOpenDeleteAllConfirmation()
                                        viewModel.deletarTudo()
                                        viewModel.onCloseMenu()
                                    }
                                )
                            }
                        }

//                        IconButton(onClick = {
//
//                        }) {
//                            Icon(
//                                imageVector = Icons.Default.Menu,
//                                contentDescription = "More",
//                                tint = AppColors.onPrimary(),
//                            )
//                        }
                    }
                }
            }
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .background(AppColors.surface())
            ) {
                NavigationBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                        .border(
                            width = 1.dp,
                            color = AppColors.outlineVariant(),
                            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                        ),
                    containerColor = AppColors.surface(),
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
                            onAddClick()
                        },
                        icon = Icons.Default.Add,
                        label = "Adicionar"
                    )

                    BottomAppBarItem(
                        selected = selectedItem == 2,
                        onClick = {
                            selectedItem = 2
                            onDashboardClick()
                        },
                        icon = Icons.Default.BarChart,
                        label = "Dashboard"
                    )
                }
            }
        },

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

            // SEÇÃO: Resumo do dia (Estática, não rola com a lista)

            Text(
                text = "Minhas anotações",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = AppColors.onBackground(),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = AppColors.surface()),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)

                ) {
                    Column(
                        modifier = Modifier.padding(2.dp),
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        NotesCard(onClick = onNotesClick)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // SEÇÃO: Registros recentes (Título fixo acima da lista)
            Text(
                text = "Registros recentes",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = AppColors.onBackground(),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // LISTA DE REGISTROS (Apenas esta parte é rolável)
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 24.dp)
            ) {
                items(lista) { item ->

                    CardListItem(
                        item = item,
                        onEditClick = onEditClick
                    )

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

// Componente para os itens da lista de registros recent