package com.tiago.annoter.ui.features.notedetail

interface NoteDetailAction {
    data object GoBack : NoteDetailAction
    data object Delete: NoteDetailAction
}