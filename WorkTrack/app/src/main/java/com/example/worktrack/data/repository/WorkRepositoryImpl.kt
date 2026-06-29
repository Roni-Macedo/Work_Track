package com.example.worktrack.data.repository

import com.example.worktrack.data.local.WorkDao
import com.example.worktrack.data.mapper.toDomain
import com.example.worktrack.data.mapper.toEntity
import com.example.worktrack.domain.model.Work
import com.example.worktrack.domain.repository.WorkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class WorkRepositoryImpl(
    private val dao: WorkDao
) : WorkRepository {

    override suspend fun insert(work: Work) {
        dao.insert(work.toEntity())
    }

    override suspend fun update(work: Work) {
        dao.update(work.toEntity())
    }

    override suspend fun delete(work: Work) {
        dao.delete(work.toEntity())
    }

    override suspend fun deleteAll() {
        dao.deleteAll()
    }

    override fun getAll(): Flow<List<Work>> {
        return dao.getAll().map { list ->
            list.map { it.toDomain() }
        }
    }
}
