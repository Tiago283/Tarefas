package com.tiago.tarefas.ui.tasklist

import androidx.compose.foundation.text.input.TextFieldState
import com.tiago.tarefas.models.Task

data class TaskListState(
    val taskList: List<Task> = emptyList(),
    val newTaskTextFieldState: TextFieldState = TextFieldState(""),
    val isLoading: Boolean = false,
)