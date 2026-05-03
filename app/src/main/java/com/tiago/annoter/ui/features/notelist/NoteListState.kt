package com.tiago.annoter.ui.features.notelist

import com.tiago.annoter.domain.model.NoteModel


data class NoteListState(
    val noteList: List<NoteModel> = emptyList(),
    val isLoading: Boolean = true,
)
