package com.tiago.annoter.ui.features.notedetail.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.tiago.annoter.R
import com.tiago.annoter.ui.features.notedetail.NoteDetailAction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailAppBar(
    titleState: TextFieldState,
    backStack: NavBackStack<NavKey>,
    modifier: Modifier = Modifier,
    onAction: (NoteDetailAction) -> Unit
) {
    var lastClickTime by remember {
        mutableLongStateOf(0L)
    }

    CenterAlignedTopAppBar(
         navigationIcon = {
            IconButton(
                onClick = {
                    val currentTime = System.currentTimeMillis()

                    if (currentTime - lastClickTime >= 1000L) {
                        lastClickTime = currentTime

                        onAction(NoteDetailAction.GoBack)

                        backStack.removeLastOrNull()
                    }
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_back),
                    contentDescription = null
                )
            }
        },
        title = {
            OutlinedTextField(
                state = titleState,
                modifier = Modifier
                    .fillMaxWidth(),
                lineLimits = TextFieldLineLimits.SingleLine,
                textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Sentences
                ),
                placeholder = {
                    Text(stringResource(R.string.tilte), fontSize = 20.sp, fontWeight = FontWeight.Bold)
                },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        },
        actions = {
            IconButton(
                onClick = {
                    val currentTime = System.currentTimeMillis()

                    if (currentTime - lastClickTime >= 1000L) {
                        lastClickTime = currentTime

                        backStack.removeLastOrNull()

                        onAction(NoteDetailAction.Delete)
                    }
                },
                modifier = Modifier
                    .padding(bottom = 10.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_delete),
                    contentDescription = null
                )
            }
        },
        modifier = modifier
    )
}