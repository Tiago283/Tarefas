package com.tiago.tarefas.ui.tasklist

sealed interface TaskAction {
    data object CreateTask : TaskAction
    data class EditTask(val taskId: Int) : TaskAction
    data class DeleteTask(val taskId: Int) : TaskAction
    data class OnCheckTask(val taskId: Int, val value: Boolean) : TaskAction
}