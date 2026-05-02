package com.tiago.tarefas.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class NoteModel(
    val title: String,
    val note: String
)