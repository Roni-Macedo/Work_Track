package com.example.worktrack.navigation

import androidx.compose.runtime.*
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.worktrack.ui.presentation.screens.add.AddScreen
import com.example.worktrack.ui.presentation.screens.dashboard.DashboardScreen
import com.example.worktrack.ui.presentation.screens.edit.EditScreen
import com.example.worktrack.ui.presentation.screens.home.HomeScreen
import com.example.worktrack.ui.presentation.screens.notes.NotesScreen

@Composable
fun AppNavigation() {

    val backStack = rememberNavBackStack(Home)

    NavDisplay(
        backStack = backStack,
        onBack = {
            if (backStack.isNotEmpty()) {
                backStack.removeAt(backStack.size - 1)
            }
        }
    ) { route ->
        NavEntry(route) {
            when(route){
                Home -> {
                    HomeScreen(
                        onAddClick = {
                            backStack.add(Add)
                        },
                        onDashboardClick = {
                            backStack.add(Dashboard)
                        },
                        onHomeClick = {
                            // Já está na Home
                        },
                        onEditClick = { id ->
                            backStack.add(Edit(id))
                        },
                        onNotesClick = {
                            backStack.add(Notes)
                        }
                    )
                }

                Notes -> {
                    NotesScreen(
                        onBack = {
                            if (backStack.isNotEmpty()) {
                                backStack.removeAt(backStack.size - 1)
                            }
                        }
                    )
                }

                Add -> {
                    AddScreen(
                        onBack = {
                            if (backStack.isNotEmpty()) {
                                backStack.removeAt(backStack.size - 1)
                            }
                        }
                    )
                }

                is Edit -> {
                    EditScreen(
                        id = route.id,
                        onBack = {
                            if (backStack.isNotEmpty()) {
                                backStack.removeAt(backStack.size - 1)
                            }
                        }
                    )
                }

                Dashboard -> {
                    DashboardScreen(
                        onBack = {
                            if (backStack.isNotEmpty()) {
                                backStack.removeAt(backStack.size - 1)
                            }
                        },
                        onHomeClick = {
                            if (backStack.isNotEmpty()) {
                                backStack.removeAt(backStack.size - 1)
                            }
                        },
                        onAddClick = {
                            backStack.add(Add)
                        },
                        onDashboardClick = {
                            // Já está no Dashboard
                        }
                    )
                }
                
            }
        }
    }
}
