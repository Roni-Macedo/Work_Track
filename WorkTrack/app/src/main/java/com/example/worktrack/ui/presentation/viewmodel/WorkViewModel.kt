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

    val localCounts = repository.getLocalCount()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val note = repository.getNote()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), "")

    val workCounts = works.map { list ->
        list.groupingBy { it.local }.eachCount()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyMap())

    // Estados de UI (Diálogos e Menus)
    var showDialog by mutableStateOf(false)
        private set

    var showDeleteAllConfirmation by mutableStateOf(false)
        private set

    var showMenu by mutableStateOf(false)
        private set

    // --- ESTADOS DOS CAMPOS DE TEXTO (MVVM) ---
    var localInput by mutableStateOf("")
        private set
    var diaSemanaInput by mutableStateOf("")
        private set
    var dataInput by mutableStateOf("")
        private set

    var selectedWork by mutableStateOf<Work?>(null)
        private set

    // Funções para atualizar os inputs
    fun onLocalChange(newValue: String) { localInput = newValue }
    fun onDiaSemanaChange(newValue: String) { diaSemanaInput = newValue }
    fun onDataChange(newValue: String) { dataInput = newValue }

    fun clearInputs() {
        localInput = ""
        diaSemanaInput = ""
        dataInput = ""
        selectedWork = null
    }

    fun loadWorkToEdit(work: Work) {
        selectedWork = work
        localInput = work.local
        diaSemanaInput = work.dayOfWeek
        dataInput = work.date
    }
    // ------------------------------------------

    fun onCloseDialog() {
        showDialog = false
        clearInputs()
    }

    fun onOpenMenu() {
        showMenu = true
    }

    fun onCloseMenu() {
        showMenu = false
    }

    fun onOpenDeleteAllConfirmation() {
        showDeleteAllConfirmation = true
    }

    fun onCloseDeleteAllConfirmation() {
        showDeleteAllConfirmation = false
    }

    // Agora a função salvar não precisa de parâmetros, ela usa o estado interno
    fun salvar() {
        if (localInput.isBlank()) return

        val work = Work(
            id = 0,
            local = localInput,
            dayOfWeek = diaSemanaInput,
            date = dataInput
        )

        viewModelScope.launch {
            repository.insert(work)
            clearInputs()
            onCloseDialog()
        }
    }

    fun deletar(work: Work) {
        viewModelScope.launch {
            repository.delete(work)
        }
    }

    // Função atualizar agora usa os inputs do ViewModel
    fun atualizar() {
        val work = selectedWork ?: return
        viewModelScope.launch {
            repository.update(
                work.copy(
                    local = localInput,
                    dayOfWeek = diaSemanaInput,
                    date = dataInput
                )
            )
            clearInputs()
        }
    }

    fun deletarTudo() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }

    fun salvarNota(content: String) {
        viewModelScope.launch {
            repository.saveNote(content)
        }
    }
}
