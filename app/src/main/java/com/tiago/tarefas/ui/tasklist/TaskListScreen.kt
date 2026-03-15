package com.tiago.tarefas.ui.tasklist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tiago.tarefas.R
import com.tiago.tarefas.models.TaskModel
import com.tiago.tarefas.ui.components.NewTaskTextField
import com.tiago.tarefas.ui.components.TaskComponent
import com.tiago.tarefas.ui.theme.TarefasTheme

@Composable
fun TaskListScreen(
    taskListViewmodel: TaskListViewmodel = hiltViewModel()
) {
    val taskListUiState by taskListViewmodel.uiState.collectAsStateWithLifecycle()

    TaskListContent(
        uiState = taskListUiState,
        onCheckTask = { taskId, value ->
            taskListViewmodel.checkTask(taskId, value)
        },
        deleteTask = { taskId ->
            taskListViewmodel.deleteTask(
                taskId
            )
        },
        createTask = {
            taskListViewmodel.createTask()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListContent(
    uiState: TaskListState,
    modifier: Modifier = Modifier,
    onCheckTask: (taskId: Int, value: Boolean) -> Unit,
    deleteTask: (taskId: Int) -> Unit,
    createTask: () -> Unit
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .imePadding(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.app_name)) }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NewTaskTextField(
                state = uiState.newTaskTextFieldState,
                createTask = createTask
            )
            when {
                uiState.isLoading -> {
                    Column(
                        modifier = Modifier
                            .weight(1f),
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
                                task,
                                onCheckTask,
                                deleteTask
                            )
                            HorizontalDivider()
                        }
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun TaskListPreview() {
    TarefasTheme {
        TaskListContent(
            uiState = TaskListState(
                taskList = listOf(
                    TaskModel(1, true, "Any"),
                    TaskModel(2, false, "Any"),
                    TaskModel(3, false, "Any")
                ),
                isLoading = false
            ),
            onCheckTask = { _, _ -> },
            deleteTask = {},
            createTask = {}
        )
    }
}