package com.tiago.tarefas.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.tiago.tarefas.models.NoteModel
import com.tiago.tarefas.ui.tasklist.TaskListScreen
import kotlinx.serialization.Serializable
import kotlin.collections.listOf

@Serializable
data object TaskList : NavKey
@Serializable
data object NoteList : NavKey
@Serializable
data class NoteDetail(val note: NoteModel) : NavKey

@Composable
fun NavigationRoot(modifier: Modifier = Modifier) {
    val backStack = rememberNavBackStack(TaskList)

    NavDisplay(
        backStack = backStack,
        modifier = modifier
            .fillMaxSize(),
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<TaskList> { TaskListScreen() }
            entry<NoteList> { }
            entry<NoteDetail> { }
        }
    )
}