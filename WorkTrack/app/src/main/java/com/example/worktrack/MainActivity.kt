package com.example.worktrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.worktrack.navigation.AppNavigation
import com.example.worktrack.ui.BaseTela
import com.example.worktrack.ui.theme.WorkTrackTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WorkTrackTheme {
                AppNavigation()
//                BaseTela()
            }
        }
    }
}
