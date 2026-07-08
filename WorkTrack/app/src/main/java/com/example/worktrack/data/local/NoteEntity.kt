package com.example.worktrack.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey
    val id: Int = 1, // Usaremos id fixo para ter apenas uma nota global, ou remova se quiser várias
    val content: String
)
