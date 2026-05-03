package com.tiago.annoter.ui.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.tiago.annoter.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TarefasAppBar(title: Int?, modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = { Text(stringResource(title ?: R.string.app_name)) },
        modifier = modifier
    )
}
