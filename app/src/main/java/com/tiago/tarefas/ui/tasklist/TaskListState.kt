package com.tiago.tarefas.ui.tasklist

import androidx.compose.foundation.text.input.TextFieldState
import com.tiago.tarefas.models.TaskModel

data class TaskListState(
    val taskList: List<TaskModel> = emptyList(),
    val newTaskTextFieldState: TextFieldState = TextFieldState(""),
    val isLoading: Boolean = true,
)