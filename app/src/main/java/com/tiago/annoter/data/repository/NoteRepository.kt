package com.tiago.annoter.data.repository

import com.tiago.annoter.data.local.NoteDao
import com.tiago.annoter.data.local.NoteEntity
import com.tiago.annoter.data.mapper.toNoteEntity
import com.tiago.annoter.domain.model.NoteModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteDao: NoteDao
) {
    fun getAll(): Flow<List<NoteEntity>> = noteDao.getAll()

    fun getNoteById(noteId: Int) = noteDao.getNoteById(noteId)

    suspend fun insertNote(note: NoteModel) = noteDao.insertNote(note.toNoteEntity())

    suspend fun updateNote(note: NoteModel) = noteDao.updateNote(note.toNoteEntity())

    suspend fun deleteNote(note: NoteModel) = noteDao.deleteNote(note.toNoteEntity())
}