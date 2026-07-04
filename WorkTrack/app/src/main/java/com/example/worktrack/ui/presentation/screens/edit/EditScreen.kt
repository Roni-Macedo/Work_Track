package com.example.worktrack.ui.presentation.screens.edit

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun EditScreen(
    id: Long,
    onBack: () -> Unit = {}
) {
    Text("Edit Screen for ID: $id")
}
