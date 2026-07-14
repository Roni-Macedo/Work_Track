package com.example.worktrack.ui.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BusinessCenter
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.worktrack.domain.model.Work
import com.example.worktrack.ui.common.AppColors
import com.example.worktrack.ui.common.AppIcon

@Composable
fun CardListItem(item: Work, onEditClick: (Long) -> Unit = {},) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = AppColors.surface()),
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
                    .background(if (item.local.trim() == "Casa") {
                        AppColors.tertiary().copy(alpha = 0.8f)
                    } else AppColors.secondary()),
                contentAlignment = Alignment.Center
            ) {
                if (item.local.trim() == "Casa"){

                    AppIcon(imageVector = Icons.Default.Home, onClick = { onEditClick(item.id.toLong()) })

                } else{

                    AppIcon(imageVector = Icons.Default.BusinessCenter, onClick = { onEditClick(item.id.toLong()) })

                }

            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.local,
                    style = MaterialTheme.typography.titleSmall,
                    letterSpacing = 1.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.onSurface()
                )
                Text(
                    text = item.dayOfWeek,
                    style = MaterialTheme.typography.bodySmall,
                    letterSpacing = 1.sp,
                    color = AppColors.onSurfaceVariant()
                )
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = item.date,
                    style = MaterialTheme.typography.labelSmall,
                    letterSpacing = 1.sp,
                    color = AppColors.onSurfaceVariant()
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            AppIcon(
                imageVector = Icons.Default.ChevronRight,
                color = AppColors.onSurfaceVariant(),
                modifier = Modifier.size(24.dp),
                onClick = { onEditClick(item.id.toLong()) }
            )

        }
    }
}
