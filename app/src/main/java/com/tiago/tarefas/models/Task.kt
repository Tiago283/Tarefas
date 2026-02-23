package com.tiago.tarefas.models

data class Task(
    val id: Int,
    val isChecked: Boolean = false,
    val task: String = ""
)