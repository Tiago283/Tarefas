package com.tiago.annoter.ui.features.notelist.components

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.tiago.annoter.R

@Composable
fun CreateNoteFab(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_add),
            contentDescription = stringResource(R.string.add_task)
        )
    }
}