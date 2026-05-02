package com.tiago.tarefas.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.tiago.tarefas.R
import com.tiago.tarefas.ui.components.TarefasAppBar
import com.tiago.tarefas.ui.navigation.components.AppNavigationBar
import com.tiago.tarefas.ui.features.notelist.NoteListScreen
import com.tiago.tarefas.ui.features.tasklist.TaskListScreen
import kotlin.collections.listOf

@Composable
fun NavigationRoot(modifier: Modifier = Modifier) {
    val backStack = rememberNavBackStack(Route.TaskList)
    val currentRoute = backStack.last()

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .imePadding(),
        topBar = {
            TarefasAppBar(
                title = if (currentRoute == Route.TaskList) R.string.app_name else R.string.notes
            )
        },
        bottomBar = {
            AppNavigationBar(
                selectedKey = currentRoute,
                onSelectKey = { backStack.add(it) }
            )
        }
    ) { innerPadding ->
        NavDisplay(
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            modifier = modifier
                .padding(innerPadding),
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            entryProvider = entryProvider {
                entry<Route.TaskList> { TaskListScreen() }
                entry<Route.NoteList> { NoteListScreen() }
                entry<Route.NoteDetail> { }
            }
        )
    }
}