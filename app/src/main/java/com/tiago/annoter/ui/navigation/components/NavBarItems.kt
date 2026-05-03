package com.tiago.annoter.ui.navigation.components

import com.tiago.annoter.R
import com.tiago.annoter.ui.navigation.Route

data class NavBarItem(
    val icon: Int,
    val title: Int
)

val TOP_LEVEL_DESTINATIONS = mapOf(
    Route.TaskList to NavBarItem(icon = R.drawable.ic_confirm, title = R.string.app_name),
    Route.NoteList to NavBarItem(icon = R.drawable.ic_note, title = R.string.notes)
)