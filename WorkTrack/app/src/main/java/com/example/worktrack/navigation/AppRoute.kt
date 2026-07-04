package com.example.worktrack.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object Home : NavKey

@Serializable
data object Add : NavKey

@Serializable
data class Edit(
    val id: Long
) : NavKey

@Serializable
data object Dashboard : NavKey
