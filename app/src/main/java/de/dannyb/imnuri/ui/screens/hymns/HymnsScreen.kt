package de.dannyb.imnuri.ui.screens.hymns

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.dannyb.imnuri.domain.model.HymnModel
import de.dannyb.imnuri.ui.components.MyToolbar

@Composable
fun HymnsScreen(hymnsViewModel: HymnsViewModel = viewModel()) {
    val screenState = hymnsViewModel.screenState.collectAsState()
    hymnsViewModel.getAllHymns()

    Scaffold(
        topBar = {
            MyToolbar(screenState.value.toolbarState) { query ->
                hymnsViewModel.getAllHymns(query)
            }
        },
    ) {
        HymnListScreen(it, screenState.value.hymns) { hymn ->
            println("Clicked hymn number ${hymn.number}")
        }
    }
}


@Composable
private fun HymnListScreen(
    paddingValues: PaddingValues,
    hymns: List<HymnModel>,
    onHymnClick: (HymnModel) -> Unit
) {
    LazyColumn(contentPadding = paddingValues) {
        items(hymns) { hymn ->
            HymnItem(hymn, onHymnClick)
        }
    }
}

@Composable
private fun HymnItem(hymn: HymnModel, onClick: (HymnModel) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(hymn) }
            .padding(16.dp)
    ) {
        Text(text = "${hymn.number}.", fontWeight = FontWeight.Bold)
        Spacer(Modifier.width(4.dp))
        Text(text = hymn.title)
    }
}
