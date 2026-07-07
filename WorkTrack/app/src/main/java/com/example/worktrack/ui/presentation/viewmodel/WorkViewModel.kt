package com.example.worktrack.ui.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.worktrack.domain.model.Work
import com.example.worktrack.domain.repository.WorkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class WorkViewModel @Inject constructor(
    private val repository: WorkRepository
) : ViewModel() {

    val works = repository.getAll()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val workCounts = works.map { list ->
        list.groupingBy { it.local }.eachCount()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyMap())

    // Estados de UI
    var showDialog by mutableStateOf(false)
        private set

    var showUpdateDialog by mutableStateOf(false)
        private set

    var showDeleteAllConfirmation by mutableStateOf(false)
        private set

    var showSearchDialog by mutableStateOf(false)
        private set

    var showMenu by mutableStateOf(false)
        private set

    var selectedWork by mutableStateOf<Work?>(null)
        private set

    fun onOpenDialog() {
        showDialog = true
    }

    fun onCloseDialog() {
        showDialog = false
    }

    fun onOpenSearchDialog() {
        showSearchDialog = true
    }

    fun onCloseSearchDialog() {
        showSearchDialog = false
    }

    fun onOpenMenu() {
        showMenu = true
    }

    fun onCloseMenu() {
        showMenu = false
    }

    fun onOpenUpdateDialog(work: Work) {
        selectedWork = work
        showUpdateDialog = true
    }

    fun onCloseUpdateDialog() {
        showUpdateDialog = false
        selectedWork = null
    }

    fun onOpenDeleteAllConfirmation() {
        showDeleteAllConfirmation = true
    }

    fun onCloseDeleteAllConfirmation() {
        showDeleteAllConfirmation = false
    }

    fun salvar(local: String, diaSemana: String, data: String) {
        val work = Work(
            id = 0,
            local = local,
            dayOfWeek = diaSemana,
            date = data
        )

        viewModelScope.launch {
            repository.insert(work)
            onCloseDialog()
        }
    }

    fun deletar(work: Work) {
        viewModelScope.launch {
            repository.delete(work)
            onCloseUpdateDialog()
        }
    }

    fun atualizar(work: Work) {
        viewModelScope.launch {
            repository.update(work)
            onCloseUpdateDialog()
        }
    }

    fun deletarTudo() {
        viewModelScope.launch {
            repository.deleteAll()
            onCloseDeleteAllConfirmation()
        }
    }
}
