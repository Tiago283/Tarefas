package com.tiago.annoter.data.repository

import com.tiago.annoter.data.local.NoteDao
import com.tiago.annoter.data.local.NoteEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteDao: NoteDao
) {
    fun getAll(): Flow<List<NoteEntity>> = noteDao.getAll()
}