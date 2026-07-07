package com.example.worktrack.ui.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.worktrack.R
import com.example.worktrack.ui.theme.WorkTrackTheme

@Composable
fun NotesCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(130.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFBF9FF) // Tom suave de roxo/branco
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
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
                    .size(85.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Textos (Título e Descrição)
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {

                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = "Registre ideias, lembretes e informações importantes.",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 13.sp,
                        lineHeight = 18.sp,
                        color = Color(0xFF6B7280)
                    )
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Botão com seta circular
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF3EFFF)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Abrir anotações",
                    tint = Color(0xFF7C4DFF), // PurpleAccent do tema
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}
