package com.tiago.tarefas.ui.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tiago.tarefas.R
import com.tiago.tarefas.models.TaskModel
import com.tiago.tarefas.ui.theme.TarefasTheme

@Composable
fun TaskComponent(
    task: TaskModel,
    onCheckTask: (taskId: Int, value: Boolean) -> Unit,
    deleteTask: (taskId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var scrollState by remember { mutableStateOf(ScrollState(1)) }

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
        if (task.isChecked) {
            Text(
                text = task.task,
                modifier = Modifier
                    .horizontalScroll(scrollState)
                    .weight(1f),
                fontStyle = FontStyle.Italic,
                textDecoration = TextDecoration.LineThrough,
                maxLines = 1
            )
        } else {
            Text(
                text = task.task,
                modifier = Modifier
                    .horizontalScroll(scrollState)
                    .weight(1f),
                fontStyle = FontStyle.Normal,
                textDecoration = TextDecoration.None,
                maxLines = 1
            )
        }
        Row(
            modifier = Modifier
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                onClick = {}
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_edit),
                    contentDescription = null
                )
            }
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
}

@Preview
@Composable
private fun TaskComponentPreview() {
    TarefasTheme {
        TaskComponent(
            task = TaskModel(
                id = 0,
                isChecked = false,
                task = "Example Task"
            ),
            onCheckTask = { _, _  -> },
            deleteTask = {}
        )
    }
}