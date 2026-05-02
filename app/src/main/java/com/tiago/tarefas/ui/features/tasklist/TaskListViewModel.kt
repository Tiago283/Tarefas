package com.tiago.tarefas.ui.features.tasklist

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tiago.tarefas.data.local.TaskEntity
import com.tiago.tarefas.data.repository.TaskRepository
import com.tiago.tarefas.domain.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(TaskListState())
    val uiState: StateFlow<TaskListState> = _uiState.asStateFlow()

    init {
        loadTasks()
    }

    fun onAction(action: TaskAction) {
        when(action) {
            TaskAction.CreateTask -> createTask()
            is TaskAction.EditTask -> editTask(action.taskId)
            is TaskAction.OnCheckTask -> checkTask(action.taskId, action.value)
            is TaskAction.DeleteTask -> deleteTask(action.taskId)
        }
    }

    private fun loadTasks() {
        viewModelScope.launch {
            repository.getAll()
                .map { list ->
                    list
                        .map { task -> task.toTaskModel() }
                        .sortedBy { task -> task.isChecked }
                }
                .collect { tasks ->
                    _uiState.update {
                        it.copy(taskList = tasks, isLoading = false)
                    }
                }
        }
    }

    private fun checkTask(taskId: Int, value: Boolean) {
        viewModelScope.launch {
            val task = _uiState.value.taskList
                .first { it.id == taskId }
                .copy(isChecked = value)

            repository.updateTask(task.toTaskEntity())
        }
    }

    private fun createTask() {
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

    private fun editTask(taskId: Int) {
        viewModelScope.launch {
            val task = _uiState.value.taskList
                .first { it.id == taskId}

            _uiState.update {
                it.copy(
                    newTaskTextFieldState = TextFieldState(task.task)
                )
            }

            repository.deleteTask(task.toTaskEntity())
        }
    }

    private fun deleteTask(taskId: Int) {
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