package com.tiago.annoter.ui.features.notelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tiago.annoter.data.mapper.toNoteModel
import com.tiago.annoter.data.repository.NoteRepository
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