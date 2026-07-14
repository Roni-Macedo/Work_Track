package com.example.worktrack.ui.presentation.screens.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddHomeWork
import androidx.compose.material.icons.filled.BusinessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.filled.WorkOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ChartRow(local: String, count: Int, progress: Float, color: Color) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

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
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = local,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                letterSpacing = 1.sp,
                color = Color(0xFF1F2937),
            )

        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .weight(1f)
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp)),
                color = color,
                trackColor = Color(0xFFF3F4F6),
                strokeCap = StrokeCap.Round
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = count.toString(),
                modifier = Modifier.width(24.dp),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                letterSpacing = 1.sp,
                color = Color(0xFF1F2937),
                textAlign = TextAlign.End
            )
        }
    }

}

fun getLocalColor(index: Int, local: String): Color {
    return when (local.lowercase().trim()) {
        "empresa" -> Color(0xFF1E88E5)
        "parque magic city" -> Color(0xFF1E88E5)
        "casa" -> Color(0xFF42BB83)
        "folga" -> Color(0xFF42BB83)
        "mercado" -> Color(0xFF7C4DFF)
        "oficina" -> Color(0xFFFFB300)
        else -> {
            val genericColors = listOf(
                Color(0xFF00BCD4),
                Color(0xFFE91E63),
                Color(0xFF8BC34A),
                Color(0xFFFF5722),
                Color(0xFF607D8B)
            )
            genericColors[index % genericColors.size]
        }
    }
}

fun getLocalIcon(local: String): ImageVector {
    return when (local.lowercase().trim()) {
        "empresa" -> Icons.Default.BusinessCenter
        "parque magic city" -> Icons.Default.BusinessCenter
        "casa" -> Icons.Default.Home
        "folga" -> Icons.Default.Home
        "mercado" -> Icons.Default.BusinessCenter
        "oficina" -> Icons.Default.BusinessCenter
        else -> Icons.Default.BusinessCenter
    }
}
