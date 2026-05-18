package com.tiago.annoter.ui.features.notedetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.tiago.annoter.R
import com.tiago.annoter.domain.model.NoteModel
import com.tiago.annoter.ui.features.notedetail.components.NoteDetailAppBar
import com.tiago.annoter.ui.theme.AnnoterTheme

@Composable
fun NoteDetailScreen(
    note: NoteModel,
    backStack: NavBackStack<NavKey>,
    noteDetailViewModel: NoteDetailViewModel = hiltViewModel()
) {
    val noteDetailUiState by noteDetailViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(note.id) {
        noteDetailViewModel.loadNote(noteId = note.id)
    }

    NoteDetailContent(
        uiState = noteDetailUiState,
        onAction = noteDetailViewModel::onAction,
        backStack = backStack
    )
}

@Composable
fun NoteDetailContent(
    uiState: NoteDetailState,
    backStack: NavBackStack<NavKey>,
    modifier: Modifier = Modifier,
    onAction: (NoteDetailAction) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
    ) {
        NoteDetailAppBar(
            titleState = uiState.titleTextFieldState,
            onAction = onAction,
            backStack = backStack
        )
        OutlinedTextField(
            state = uiState.noteTextFieldState,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            lineLimits = TextFieldLineLimits.MultiLine(),
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Sentences
            ),
            placeholder = { Text(stringResource(R.string.note)) },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
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
            backStack = NavBackStack(),
            onAction = {}
        )
    }
}