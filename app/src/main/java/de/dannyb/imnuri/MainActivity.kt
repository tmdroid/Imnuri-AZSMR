package de.dannyb.imnuri

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import de.dannyb.imnuri.domain.model.HymnModel
import de.dannyb.imnuri.hymns.HymnsViewModel
import de.dannyb.imnuri.ui.components.Toolbar
import de.dannyb.imnuri.ui.theme.ImnuriTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ImnuriTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

                    HymnListScreen { hymn ->
                        println("Clicked hymn number ${hymn.number}")
                    }

                }
            }
        }
    }
}

@Composable
fun HymnListScreen(hymnsViewModel: HymnsViewModel = viewModel(), onHymnClick: (HymnModel) -> Unit) {
    val hymns = hymnsViewModel.getAllHymns().collectAsState(initial = emptyList())

    LazyColumn {
        items(hymns.value) { hymn ->
            HymnItem(hymn, onHymnClick)
        }
    }
}

@Composable
fun HymnItem(hymn: HymnModel, onClick: (HymnModel) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(hymn) }
            .padding(16.dp)
    ) {
        Text(text = hymn.number.toString(), fontWeight = FontWeight.Bold)
        Spacer(Modifier.width(16.dp))
        Text(text = hymn.title)
    }
}