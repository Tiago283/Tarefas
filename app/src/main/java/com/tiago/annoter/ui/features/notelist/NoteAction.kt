package com.tiago.annoter.ui.features.notelist

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

interface NoteAction {
    data object OnFabClicked : NoteAction
    data class OnNoteClicked(val noteId: Int, val backStack: NavBackStack<NavKey>) : NoteAction
}