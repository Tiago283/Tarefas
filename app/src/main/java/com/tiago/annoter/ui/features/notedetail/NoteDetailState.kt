package com.tiago.annoter.ui.features.notedetail

import androidx.compose.foundation.text.input.TextFieldState
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.tiago.annoter.domain.model.NoteModel

data class NoteDetailState(
    val note: NoteModel = NoteModel(0, "", ""),
    val backStack: NavBackStack<NavKey> = NavBackStack(),
    val titleTextFieldState: TextFieldState = TextFieldState(),
    val noteTextFieldState: TextFieldState = TextFieldState(),
    val isCreating: Boolean = false
)
