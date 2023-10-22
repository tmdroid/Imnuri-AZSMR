package de.dannyb.imnuri.ui.screens.settings

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.jamal.composeprefs3.ui.PrefsScreen
import com.jamal.composeprefs3.ui.prefs.ListPref
import com.jamal.composeprefs3.ui.prefs.SwitchPref
import de.dannyb.imnuri.ext.hymnsAppToolbarColors

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(viewModel: SettingsViewModel, onBackPressed: () -> Unit) {
    TopAppBar(title = { Text(text = "Settings") }, navigationIcon = {
        IconButton(onClick = { onBackPressed() }) {
            Icon(Icons.Default.ArrowBack, contentDescription = "")
        }
    },
        colors = TopAppBarDefaults.hymnsAppToolbarColors()
    )

    PrefsScreen(
        dataStore = LocalContext.current.dataStore,
        modifier = Modifier.padding(top = 55.dp),
    ) {
        prefsGroup("Aspect") {
            prefsItem {
                SwitchPref(key = "dark_mode", title = "Dark Mode", summary = "Enable dark mode")
            }

            prefsItem {
                ListPref(
                    key = "language",
                    title = "Hymns Language",
                    summary = "What language you want your hymsn in?",
                    defaultValue = "Romanian",
                    entries = mapOf(
                        "ro" to "Romanian",
                        "de" to "German",
                        "it" to "Italian",
                    )
                )
            }
        }
    }
}