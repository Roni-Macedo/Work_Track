package com.example.worktrack.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [WorkEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun workDao(): WorkDao
}
