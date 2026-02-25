package com.tiago.tarefas.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tiago.tarefas.R
import com.tiago.tarefas.models.Task
import com.tiago.tarefas.ui.theme.TarefasTheme

@Composable
fun TaskComponent(
    task: Task,
    onCheckTask: (taskId: Int, value: Boolean) -> Unit,
    deleteTask: (taskId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Checkbox(
            checked = task.isChecked,
            onCheckedChange = { value ->
                onCheckTask(task.id, value)
            },
            modifier = Modifier
                .padding(5.dp)
        )
        Text(
            text = task.task
        )
        Spacer(Modifier.weight(1f))
        IconButton(
            onClick = { deleteTask(task.id) },
            modifier = Modifier
                .padding(5.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_delete),
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
private fun TaskComponentPreview() {
    TarefasTheme {
        TaskComponent(
            task = Task(
                id = 0,
                isChecked = false,
                task = "Example Task"
            ),
            onCheckTask = { _, _  -> },
            deleteTask = {}
        )
    }
}