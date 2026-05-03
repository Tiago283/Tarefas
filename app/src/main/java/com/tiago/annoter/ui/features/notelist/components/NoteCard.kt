package com.tiago.annoter.ui.features.notelist.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tiago.annoter.domain.model.NoteModel
import com.tiago.annoter.ui.theme.AnnoterTheme

@Composable
fun NoteCard(
    note: NoteModel,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = note.title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                maxLines = 1
            )
            Text(
                text = note.note
            )
        }
    }
}

@Preview
@Composable
private fun NoteCardPreview() {
    AnnoterTheme {
        NoteCard(
            note = NoteModel(0, "Title", "Note")
        ) { }
    }
}