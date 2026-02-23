package com.tiago.tarefas.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.tiago.tarefas.R
import com.tiago.tarefas.models.Task

@Composable
fun TaskRow(
    task: Task,
    onCheckTask: (taskId: Int, value: Boolean) -> Unit,
    deleteTask: (taskId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = task.isChecked,
            onCheckedChange = { value ->
                onCheckTask(task.id, value)
            }
        )
        Text(
            text = task.task
        )
        Spacer(Modifier.weight(1f))
        IconButton(
            onClick = { deleteTask(task.id) }
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_delete),
                contentDescription = null
            )
        }
    }
}