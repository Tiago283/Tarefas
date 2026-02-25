package com.tiago.tarefas.ui.tasklist

import androidx.compose.foundation.text.input.clearText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tiago.tarefas.data.entity.TaskEntity
import com.tiago.tarefas.data.local.TaskRepository
import com.tiago.tarefas.models.TaskModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TaskListViewmodel(private val repository: TaskRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(TaskListState())
    val uiState: StateFlow<TaskListState> = _uiState.asStateFlow()

    init {
        loadTasks()
    }

    private fun loadTasks() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            repository.getAll()
                .map { list -> list.map { task -> task.toTaskModel()} }
                .collect { tasks ->
                    _uiState.update {
                        it.copy(taskList = tasks, isLoading = false)
                    }
                }
        }
    }

    fun onCheckTask(taskId: Int, value: Boolean) {
        viewModelScope.launch {
            val task = _uiState.value.taskList
                .first { it.id == taskId }
                .copy(isChecked = value)

            repository.updateTask(task.toTaskEntity())
        }
        _uiState.update {
            it.copy(
                taskList = it.taskList.map { task ->
                    if (task.id == taskId) task.copy(isChecked = value)
                    else task
                }
            )
        }
    }

    fun createTask() {
        viewModelScope.launch {
            val text = _uiState.value.newTaskTextFieldState.text.toString()

            if (text.isNotBlank()) {
                repository.insertTask(
                    TaskEntity(0, false, text)
                )
            }

            _uiState.value.newTaskTextFieldState.clearText()
        }
    }

    fun deleteTask(taskId: Int) {
        viewModelScope.launch {
            val task = _uiState.value.taskList
                .first { it.id == taskId }

            repository.deleteTask(task.toTaskEntity())
        }
    }
}

fun TaskModel.toTaskEntity() : TaskEntity = TaskEntity(
    id = id,
    isChecked = isChecked,
    task = task
)

fun TaskEntity.toTaskModel() : TaskModel = TaskModel(
    id = id,
    isChecked = isChecked,
    task = task
)