package com.tiago.annoter.domain.model

data class TaskModel(
    val id: Int,
    val isChecked: Boolean = false,
    val task: String = ""
)