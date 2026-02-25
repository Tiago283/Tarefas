package com.tiago.tarefas.models

data class TaskModel(
    val id: Int,
    val isChecked: Boolean = false,
    val task: String = ""
)