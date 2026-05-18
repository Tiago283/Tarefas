package com.tiago.annoter.ui.features.notedetail

import androidx.compose.foundation.text.input.TextFieldState
import com.tiago.annoter.domain.model.NoteModel

data class NoteDetailState(
    val note: NoteModel = NoteModel(0, "", ""),
    val titleTextFieldState: TextFieldState = TextFieldState(),
    val noteTextFieldState: TextFieldState = TextFieldState(),
    val isCreating: Boolean = false
)
