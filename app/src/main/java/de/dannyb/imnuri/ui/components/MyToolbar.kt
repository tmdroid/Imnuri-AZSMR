package de.dannyb.imnuri.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import de.dannyb.imnuri.ext.hymnsAppToolbarColors
import de.dannyb.imnuri.ui.screens.hymns.HymnsToolbarState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyToolbar(toolbarState: MutableState<HymnsToolbarState>, onQueryChanged: (String) -> Unit) {
    when (toolbarState.value) {
        HymnsToolbarState.NORMAL -> {
            TopAppBar(
                colors = TopAppBarDefaults.hymnsAppToolbarColors(),
                title = { Text("My Hymnal") },
                actions = {
                    IconButton(onClick = { toolbarState.value = HymnsToolbarState.SEARCH }) {
                        Icon(Icons.Default.Search, contentDescription = "search")
                    }
                }
            )
        }

        HymnsToolbarState.SEARCH -> {
            var query by remember { mutableStateOf("") }
            val focusRequester = remember { FocusRequester() }
            val keyboardController = LocalSoftwareKeyboardController.current
            val focusManager = LocalFocusManager.current

            LaunchedEffect(Unit) {
                focusRequester.requestFocus()
            }

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(8.dp),
            ) {
                TextField(
                    value = query,
                    onValueChange = {
                        query = it
                        onQueryChanged(it)
                    },
                    label = { Text("Search hymns") },
                    leadingIcon = {
                        IconButton(
                            onClick = {
                                onQueryChanged("")
                                toolbarState.value = HymnsToolbarState.NORMAL
                            }
                        ) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "back"
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            keyboardController?.hide()
                            focusManager.clearFocus()
                        },
                    ),
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester),
                )
            }
        }
    }
}