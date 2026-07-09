package com.example.worktrack.domain.repository

import com.example.worktrack.domain.model.LocalCount
import com.example.worktrack.domain.model.Work
import kotlinx.coroutines.flow.Flow

interface WorkRepository {

    suspend fun insert(work: Work)
    suspend fun update(work: Work)
    suspend fun delete(work: Work)
    suspend fun deleteAll()

    fun getAll(): Flow<List<Work>>
    fun getLocalCount(): Flow<List<LocalCount>>

    // Notes
    suspend fun saveNote(content: String)
    fun getNote(): Flow<String>
}
