package com.example.worktrack.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun AppIcon(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    color: Color = AppColors.onPrimary(),
    onClick: () -> Unit = {}
) {
    Icon(
        imageVector = imageVector,
        contentDescription = contentDescription,
        tint = color,
        modifier = modifier
            .size(20.dp)
            .clickable { onClick() }
    )
}
