package com.tiago.annoter.ui.features.notedetail

import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.tiago.annoter.data.local.NoteEntity
import com.tiago.annoter.data.mapper.toNoteEntity
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
class NoteDetailViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(NoteDetailState())
    val uiState: StateFlow<NoteDetailState> = _uiState.asStateFlow()

    fun onAction(action: NoteDetailAction) {
        when(action) {
            is NoteDetailAction.GoBack -> {
                if (_uiState.value.isCreating) goBackAndCreateNote() else goBackAndUpdateNote()
            }
            is NoteDetailAction.Delete -> delete()
        }
    }

    fun loadNoteAndBackStack(noteId: Int, backStack: NavBackStack<NavKey>) {
        viewModelScope.launch {
            try {
                repository.getNoteById(noteId).collect { note ->
                    _uiState.update {
                        it.copy(
                            isCreating = false,
                            note = note.toNoteModel(),
                            backStack = backStack
                        )

                    }
                    _uiState.value.titleTextFieldState.setTextAndPlaceCursorAtEnd(note.title)
                    _uiState.value.noteTextFieldState.setTextAndPlaceCursorAtEnd(note.note)
                }
            } catch (_: IllegalStateException) {
                _uiState.update {
                    it.copy(
                        isCreating = true,
                        backStack = backStack
                    )
                }
            }
        }
    }

    private fun goBackAndCreateNote() {
        viewModelScope.launch {
            val titleValue = _uiState.value.titleTextFieldState.text.toString()
            val noteValue = _uiState.value.noteTextFieldState.text.toString()

            if (titleValue.isNotBlank() || noteValue.isNotBlank()) {
                repository.insertNote(
                    NoteEntity(
                        id = 0,
                        title = titleValue,
                        note = noteValue
                    )
                )
            }

            _uiState.value.backStack.removeLastOrNull()
        }
    }

    private fun goBackAndUpdateNote() {
        viewModelScope.launch {
            val newTitleValue = _uiState.value.titleTextFieldState.text.toString()
            val newNoteValue = _uiState.value.noteTextFieldState.text.toString()
            val note = _uiState.value.note.copy(title = newTitleValue, note = newNoteValue)

            repository.updateNote(note.toNoteEntity())

            _uiState.value.backStack.removeLastOrNull()
        }
    }

    private fun delete() {
        viewModelScope.launch {
            _uiState.value.backStack.removeLastOrNull()

            repository.deleteNote(_uiState.value.note.toNoteEntity())
        }
    }
}