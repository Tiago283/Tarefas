package com.tiago.annoter.ui.features.notelist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tiago.annoter.domain.model.NoteModel
import com.tiago.annoter.ui.features.notelist.components.NoteCard
import com.tiago.annoter.ui.theme.AnnoterTheme

@Composable
fun NoteListScreen(
    noteListViewModel: NoteListViewModel = hiltViewModel()
) {
    val noteListUiState by noteListViewModel.uiState.collectAsStateWithLifecycle()

    NoteListContent(
        uiState = noteListUiState
    )
}

@Composable
fun NoteListContent(
    uiState: NoteListState,
    modifier: Modifier = Modifier
) {
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
            LazyVerticalGrid(
                columns = GridCells.Adaptive(150.dp),
                modifier = modifier
                    .fillMaxSize()
            ) {
                items(uiState.noteList) { note ->
                    NoteCard(
                        note = note,
                        onClick = {}
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NoteListContentPreview() {
    AnnoterTheme {
        NoteListContent(
            uiState = NoteListState(
                noteList = listOf(
                    NoteModel(1, "Title", "Note"),
                    NoteModel(2, "Title", "Note"),
                    NoteModel(3, "Title", "Note"),
                ),
                isLoading = false
            )
        )
    }
}