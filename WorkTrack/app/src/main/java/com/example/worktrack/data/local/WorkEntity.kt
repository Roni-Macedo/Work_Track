package com.example.worktrack.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "work")
data class WorkEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val local: String,
    val dayOfWeek: String
)
