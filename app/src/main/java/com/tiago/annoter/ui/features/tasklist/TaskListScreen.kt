package com.tiago.annoter.ui.features.tasklist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tiago.annoter.domain.model.TaskModel
import com.tiago.annoter.ui.features.tasklist.components.NewTaskTextField
import com.tiago.annoter.ui.features.tasklist.components.TaskComponent
import com.tiago.annoter.ui.theme.AnnoterTheme

@Composable
fun TaskListScreen(
    taskListViewModel: TaskListViewModel = hiltViewModel()
) {
    val taskListUiState by taskListViewModel.uiState.collectAsStateWithLifecycle()

    TaskListContent(
        uiState = taskListUiState,
        onAction = taskListViewModel::onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListContent(
    uiState: TaskListState,
    modifier: Modifier = Modifier,
    onAction: (TaskAction) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NewTaskTextField(
            state = uiState.newTaskTextFieldState,
            createTask = { onAction(TaskAction.CreateTask) }
        )
        when {
            uiState.isLoading -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            }
            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    items(uiState.taskList) { task ->
                        TaskComponent(
                            task = task,
                            onAction = onAction
                        )
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskListPreview() {
    AnnoterTheme {
        TaskListContent(
            uiState = TaskListState(
                taskList = listOf(
                    TaskModel(1, true, "Task"),
                    TaskModel(2, false, "Task"),
                    TaskModel(3, false, "Task")
                ),
                isLoading = false
            ),
            onAction = {}
        )
    }
}