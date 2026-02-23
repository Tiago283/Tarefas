package com.tiago.tarefas

import android.app.Application
import androidx.room.Room
import com.tiago.tarefas.data.AppDatabase
import com.tiago.tarefas.data.local.TaskRepository

class TarefasApplication : Application() {
    val db by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "tarefas-database.db"
        ).build()
    }

    val taskRepository by lazy { TaskRepository(db.taskDao()) }
}