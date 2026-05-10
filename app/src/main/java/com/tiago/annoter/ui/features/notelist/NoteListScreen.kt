package com.tiago.annoter.ui.features.notelist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.tiago.annoter.domain.model.NoteModel
import com.tiago.annoter.ui.features.notelist.components.NoteCard
import com.tiago.annoter.ui.theme.AnnoterTheme

@Composable
fun NoteListScreen(
    noteListViewModel: NoteListViewModel = hiltViewModel(),
    backStack: NavBackStack<NavKey>
) {
    val noteListUiState by noteListViewModel.uiState.collectAsStateWithLifecycle()

    NoteListContent(
        uiState = noteListUiState,
        backStack = backStack,
        onAction = noteListViewModel::onAction
    )
}

@Composable
fun NoteListContent(
    uiState: NoteListState,
    backStack: NavBackStack<NavKey>,
    modifier: Modifier = Modifier,
    onAction: (NoteAction) -> Unit
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
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Adaptive(180.dp),
                contentPadding = PaddingValues(10.dp),
                modifier = modifier
                    .fillMaxSize()
            ) {
                items(uiState.noteList) { note ->
                    NoteCard(
                        note = note,
                        onClick = {
                            onAction(
                                NoteAction.OnNoteClicked(
                                    noteId = note.id,
                                    backStack = backStack
                                )
                            )
                        }
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
            ),
            backStack = NavBackStack(),
            onAction = {}
        )
    }
}