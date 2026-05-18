package com.tiago.annoter.ui.features.notedetail

import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tiago.annoter.data.mapper.toNoteModel
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

    fun loadNote(noteId: Int) {
        viewModelScope.launch {
            try {
                repository.getNoteById(noteId).collect { note ->
                    _uiState.update {
                        it.copy(
                            isCreating = false,
                            note = note.toNoteModel(),
                        )

                    }
                    _uiState.value.titleTextFieldState.setTextAndPlaceCursorAtEnd(note.title)
                    _uiState.value.noteTextFieldState.setTextAndPlaceCursorAtEnd(note.note)
                }
            } catch (_: IllegalStateException) {
                _uiState.update {
                    it.copy(
                        isCreating = true,
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
                    NoteModel(
                        id = 0,
                        title = titleValue,
                        note = noteValue
                    )
                )
            }
        }
    }

    private fun goBackAndUpdateNote() {
        viewModelScope.launch {
            val newTitleValue = _uiState.value.titleTextFieldState.text.toString()
            val newNoteValue = _uiState.value.noteTextFieldState.text.toString()
            val note = _uiState.value.note.copy(title = newTitleValue, note = newNoteValue)

            repository.updateNote(note)
        }
    }

    private fun delete() {
        viewModelScope.launch {
            repository.deleteNote(_uiState.value.note)
        }
    }
}