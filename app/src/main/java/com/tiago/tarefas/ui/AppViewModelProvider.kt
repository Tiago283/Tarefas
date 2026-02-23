package com.tiago.tarefas.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.tiago.tarefas.TarefasApplication
import com.tiago.tarefas.ui.tasklist.TaskListViewmodel

object AppViewModelProvider {
    var Factory = viewModelFactory {
        initializer {
            TaskListViewmodel(
                tarefasApplication().taskRepository
            )
        }
    }
}

fun CreationExtras.tarefasApplication() : TarefasApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as TarefasApplication)