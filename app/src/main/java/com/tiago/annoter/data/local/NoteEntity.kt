package com.tiago.annoter.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo("title") val title: String,
    @ColumnInfo("note") val note: String,
)
