package com.example.worktrack.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.worktrack.domain.model.LocalCount
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkDao {

    @Insert
    suspend fun insert(work: WorkEntity)

    @Update
    suspend fun update(work: WorkEntity)

    @Delete
    suspend fun delete(work: WorkEntity)

    @Query("DELETE FROM work")
    suspend fun deleteAll()

    @Query("SELECT * FROM work ORDER BY id DESC")
    fun getAll(): Flow<List<WorkEntity>>

    @Query("""
    SELECT local, COUNT(*) AS quantidade
    FROM work
    GROUP BY local
    ORDER BY quantidade DESC
""")

    fun getLocalCount(): Flow<List<LocalCount>>

    // Notes
    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity)

    @Query("SELECT content FROM notes WHERE id = 1")
    fun getNote(): Flow<String?>

}
