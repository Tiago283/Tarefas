package com.tiago.tarefas.models

import kotlinx.serialization.Serializable

@Serializable
data class NoteModel(
    val title: String,
    val note: String
)
