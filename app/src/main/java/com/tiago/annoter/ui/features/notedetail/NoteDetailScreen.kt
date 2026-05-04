package com.tiago.annoter.ui.features.notedetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tiago.annoter.domain.model.NoteModel
import com.tiago.annoter.ui.theme.AnnoterTheme

@Composable
fun NoteDetailScreen(
    note: NoteModel
) {
    NoteDetailContent(note)
}

@Composable
fun NoteDetailContent(
    note: NoteModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(note.title)
        Text(note.note)
    }
}

@Preview(showBackground = true)
@Composable
private fun NoteDetailContentPreview() {
    AnnoterTheme {
        NoteDetailContent(
            note = NoteModel(0, "Title", "Note")
        )
    }
}