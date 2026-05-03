package com.tiago.annoter.ui.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.tiago.annoter.R
import com.tiago.annoter.ui.components.TarefasAppBar
import com.tiago.annoter.ui.navigation.components.AnnoterNavigationBar
import com.tiago.annoter.ui.features.notelist.NoteListScreen
import com.tiago.annoter.ui.features.tasklist.TaskListScreen
import com.tiago.annoter.ui.navigation.components.TOP_LEVEL_DESTINATIONS
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
                title = TOP_LEVEL_DESTINATIONS[currentRoute]?.title
            )
        },
        bottomBar = {
            AnnoterNavigationBar(
                selectedKey = currentRoute,
                onSelectKey = { backStack.add(it) }
            )
        },
        floatingActionButton = {
            AnimatedVisibility(
                visible = currentRoute == Route.NoteList
            ) {
                FloatingActionButton(
                    onClick = {}
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_add),
                        contentDescription = stringResource(R.string.add_task)
                    )
                }
            }
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