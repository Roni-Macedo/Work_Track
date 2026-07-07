package com.example.worktrack.ui.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.worktrack.R
import com.example.worktrack.ui.common.AppColors
import com.example.worktrack.ui.common.AppIcon
import com.example.worktrack.ui.theme.TextPrimary
import com.example.worktrack.ui.theme.TextSecondary

@Composable
fun NotesCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(130.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = AppColors.tertiary().copy(alpha = 0.04f) // Fundo suave usando a cor da paleta
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagem do caderno (caderno.png)
            Image(
                painter = painterResource(id = R.drawable.caderno),
                contentDescription = "Caderno de anotações",
                modifier = Modifier
                    .size(80.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Textos (Título e Descrição)
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Lembretes e informações importantes.",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 13.sp,
                        lineHeight = 18.sp,
                        color = TextSecondary
                    )
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Botão com seta circular
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(AppColors.tertiary().copy(alpha = 0.1f)), // Círculo suave
                contentAlignment = Alignment.Center
            ) {
                AppIcon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    color = AppColors.tertiary(),
                    modifier = Modifier.size(20.dp),
                    onClick = { /* Ação ao clicar */ }
                )
            }
        }
    }
}
