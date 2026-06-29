package com.example.worktrack.ui.presentation.components

import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

fun getWeekDay(): String {

    val hoje = LocalDate.now()

    val locale = Locale.Builder()
        .setLanguage("pt")
        .setRegion("BR")
        .build()

    val diaSemana = hoje.dayOfWeek
        .getDisplayName(TextStyle.SHORT, locale)
        .replace(".", "")
        .replaceFirstChar { it.uppercase() }

    val mes = hoje.month
        .getDisplayName(TextStyle.SHORT, locale)
        .replace(".", "")
        .replaceFirstChar { it.uppercase() }

    val dia = hoje.dayOfMonth
        .toString()
        .padStart(2, '0')

    return "$diaSemana, $dia de $mes."
}
