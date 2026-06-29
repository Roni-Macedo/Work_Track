package com.example.worktrack.domain.model

data class Work(
    val id: Int,
    val local: String,
    val dayOfWeek: String
)

data class LocalCount(
    val local: String,
    val quantidade: Int
)
