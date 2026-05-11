package com.tiago.annoter.data.mapper

import com.tiago.annoter.data.local.NoteEntity
import com.tiago.annoter.domain.model.NoteModel

fun NoteModel.toNoteEntity() : NoteEntity = NoteEntity(
    id = id,
    title = title,
    note = note
)

fun NoteEntity.toNoteModel() : NoteModel = NoteModel(
    id = id,
    title = title,
    note = note
)