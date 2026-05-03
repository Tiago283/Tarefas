package com.tiago.annoter.ui.navigation

import androidx.navigation3.runtime.NavKey
import com.tiago.annoter.domain.model.NoteModel
import kotlinx.serialization.Serializable

interface Route : NavKey {
    @Serializable
    data object TaskList : Route
    @Serializable
    data object NoteList : Route
    @Serializable
    data class NoteDetail(val note: NoteModel) : Route
}