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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import de.dannyb.imnuri.domain.model.HymnModel
import de.dannyb.imnuri.ui.components.MyToolbar

@Composable
fun HymnsScreen(hymnsListViewModel: HymnsListViewModel, onHymnClick: (HymnModel) -> Unit) {
    val screenState = hymnsListViewModel.screenState.collectAsState()
    hymnsListViewModel.getAllHymns()

    Scaffold(
        topBar = {
            MyToolbar(screenState.value.toolbarState) { query ->
                hymnsListViewModel.getAllHymns(query)
            }
        },
    ) { paddingValues ->
        HymnListScreen(
            paddingValues = paddingValues,
            hymns = screenState.value.hymns,
            onHymnClick = { hymn ->
                screenState.value.toolbarState.value = HymnsToolbarState.NORMAL
                onHymnClick(hymn)
            },
            onFavoriteClick = { hymn -> hymnsListViewModel.toggleFavorite(hymn) })
    }
}


@Composable
private fun HymnListScreen(
    paddingValues: PaddingValues,
    hymns: List<HymnModel>,
    onHymnClick: (HymnModel) -> Unit,
    onFavoriteClick: (HymnModel) -> Unit,
) {
    LazyColumn(contentPadding = paddingValues) {
        items(hymns) { hymn ->
            HymnItem(hymn, onHymnClick, onFavoriteClick)
            Divider(color = Color.LightGray, thickness = 1.dp)
        }
    }
}

@Composable
private fun HymnItem(
    hymn: HymnModel,
    onClick: (HymnModel) -> Unit,
    onFavoriteClick: (HymnModel) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(hymn) }
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = "${hymn.number}.", fontWeight = FontWeight.Bold)
        Spacer(Modifier.width(4.dp))
        Text(text = hymn.title, modifier = Modifier.weight(1f))
        IconButton(onClick = { onFavoriteClick(hymn) }) {
            val icon =
                if (hymn.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder
            Icon(icon, contentDescription = "favorite")
        }
    }
}
