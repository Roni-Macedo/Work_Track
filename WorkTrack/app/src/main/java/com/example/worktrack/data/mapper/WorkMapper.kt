package com.example.worktrack.data.mapper

import com.example.worktrack.data.local.WorkEntity
import com.example.worktrack.domain.model.Work

fun WorkEntity.toDomain() = Work(id, local, dayOfWeek)

fun Work.toEntity() = WorkEntity(id, local, dayOfWeek)
