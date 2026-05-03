package com.tiago.annoter.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class NoteModel(
    val id: Int,
    val title: String,
    val note: String
)