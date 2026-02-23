package com.tiago.tarefas.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tiago.tarefas.data.entity.TaskEntity
import com.tiago.tarefas.data.local.TaskDao

@Database(entities = [TaskEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao() : TaskDao
}