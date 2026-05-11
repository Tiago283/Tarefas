package com.tiago.annoter.ui.features.notelist.components

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.tiago.annoter.R

@Composable
fun CreateNoteFab(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    var lastClickTime by remember {
        mutableLongStateOf(0L)
    }

    FloatingActionButton(
        onClick = {
            val currentTime = System.currentTimeMillis()

            if (currentTime - lastClickTime >= 1000L) {
                lastClickTime = currentTime

                onClick()
            }
        },
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_add),
            contentDescription = stringResource(R.string.add_task)
        )
    }
}