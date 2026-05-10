package com.tiago.annoter.ui.features.notedetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.tiago.annoter.R
import com.tiago.annoter.domain.model.NoteModel
import com.tiago.annoter.ui.theme.AnnoterTheme

@Composable
fun NoteDetailScreen(
    note: NoteModel,
    backStack: NavBackStack<NavKey>,
    noteDetailViewModel: NoteDetailViewModel = hiltViewModel()
) {
    val noteDetailUiState by noteDetailViewModel.uiState.collectAsStateWithLifecycle()
    noteDetailViewModel.loadNoteAndBackStack(noteId = note.id, backStack = backStack)

    NoteDetailContent(
        uiState = noteDetailUiState,
        onAction = noteDetailViewModel::onAction
    )
}

@Composable
fun NoteDetailContent(
    uiState: NoteDetailState,
    modifier: Modifier = Modifier,
    onAction: (NoteDetailAction) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            IconButton(
                onClick = { onAction(NoteDetailAction.GoBack) }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_back),
                    contentDescription = null
                )
            }
            OutlinedTextField(
                state = uiState.titleTextFieldState,
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .fillMaxWidth(0.9f),
                lineLimits = TextFieldLineLimits.SingleLine
            )
            IconButton(
                onClick = { onAction(NoteDetailAction.Delete) },
                modifier = Modifier
                    .padding(bottom = 10.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_delete),
                    contentDescription = null
                )
            }
        }
        OutlinedTextField(
            state = uiState.noteTextFieldState,
            modifier = Modifier
                .fillMaxSize(),
            lineLimits = TextFieldLineLimits.MultiLine()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NoteDetailContentPreview() {
    AnnoterTheme {
        NoteDetailContent(
            uiState = NoteDetailState(
                NoteModel(0, "Title", "Note")
            ),
            onAction = {}
        )
    }
}