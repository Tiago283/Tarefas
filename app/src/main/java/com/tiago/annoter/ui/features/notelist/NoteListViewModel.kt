package com.tiago.annoter.ui.features.notelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tiago.annoter.data.local.NoteEntity
import com.tiago.annoter.data.repository.NoteRepository
import com.tiago.annoter.domain.model.NoteModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(NoteListState())
    val uiState: StateFlow<NoteListState> = _uiState.asStateFlow()

    init {
        loadNotes()
    }

    fun onAction() {}

    private fun loadNotes() {
        viewModelScope.launch {
            repository.getAll()
                .collect { list ->
                    _uiState.update {
                        it.copy(
                            noteList = list.map { note ->
                                note.toNoteModel()
                            },
                            isLoading = false
                        )
                    }
                }
        }
    }
}

fun NoteModel.toNoteEntity() : NoteEntity = NoteEntity(
    id = id,
    title = title,
    note = note
)

fun NoteEntity.toNoteModel() : NoteModel = NoteModel(
    id = id,
    title = title,
    note = note
)