package com.tiago.tarefas.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo("is_checked") val isChecked: Boolean,
    @ColumnInfo("task") val task: String
)