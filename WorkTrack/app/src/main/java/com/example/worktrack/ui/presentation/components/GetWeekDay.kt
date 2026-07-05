package com.example.worktrack.ui.presentation.components

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

data class DateInfo(
    val weekDay: String,
    val date: String
)

fun getWeekDay(): DateInfo {
    val hoje = LocalDate.now()

    val locale = Locale("pt", "BR")

    val weekDay = hoje.dayOfWeek
        .getDisplayName(TextStyle.FULL, locale)
        .replaceFirstChar { it.uppercase() }

    val date = hoje.format(
        DateTimeFormatter.ofPattern("dd/MM/yyyy")
    )

    return DateInfo(
        weekDay = weekDay,
        date = date
    )
}

/*
        val dateInfo = getDateInfo()

        Text(text = dateInfo.weekDay) // Segunda-feira
        Text(text = dateInfo.date)    // 26/05/2026
 */
