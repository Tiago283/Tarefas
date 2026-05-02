package com.tiago.tarefas.ui.notelist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tiago.tarefas.ui.theme.TarefasTheme

@Composable
fun NoteListScreen() {
    NoteListContent()
}

@Composable
fun NoteListContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) { }
}

@Preview
@Composable
private fun NoteListContentPreview() {
    TarefasTheme {
        NoteListContent()
    }
}