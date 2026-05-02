package com.tiago.tarefas.ui.features.tasklist

import androidx.compose.foundation.text.input.TextFieldState
import com.tiago.tarefas.domain.model.TaskModel

data class TaskListState(
    val taskList: List<TaskModel> = emptyList(),
    val newTaskTextFieldState: TextFieldState = TextFieldState(""),
    val isLoading: Boolean = true,
)