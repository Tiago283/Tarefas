package com.tiago.annoter.ui.features.notelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.tiago.annoter.data.mapper.toNoteModel
import com.tiago.annoter.data.repository.NoteRepository
import com.tiago.annoter.ui.navigation.Route
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

    fun onAction(action: NoteAction) {
        when (action) {
            is NoteAction.OnNoteClicked -> goToNote(action.noteId, action.backStack)
            else -> {}
        }
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

    private fun goToNote(noteId: Int, backStack: NavBackStack<NavKey>) {
        backStack.add(
            Route.NoteDetail(
                _uiState.value.noteList.first {
                    it.id == noteId
                }
            )
        )
    }
}