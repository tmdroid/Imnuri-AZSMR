package de.dannyb.imnuri.ui.screens.details.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.dannyb.imnuri.domain.model.HymnModel
import de.dannyb.imnuri.ext.hymnsAppToolbarColors
import de.dannyb.imnuri.ui.screens.details.HymnDetailsOperations

@Composable
fun LyricsScreen(
    hymn: HymnModel,
    fontSize: Int,
    onBackPressed: () -> Unit,
    operations: HymnDetailsOperations,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopToolbar(
            title = hymn.category,
            isFavorite = hymn.isFavorite,
            onBackPressed = onBackPressed,
            operations = operations
        )

        Box(modifier = Modifier.weight(1f)) {
            LyricsContent(hymn, fontSize)
        }

        Box(modifier = Modifier.fillMaxWidth()) {
            FontSizeSlider(fontSize = fontSize, operations = operations)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopToolbar(
    title: String,
    isFavorite: Boolean,
    onBackPressed: () -> Unit,
    operations: HymnDetailsOperations,
) {
    TopAppBar(
        title = { Text(title, maxLines = 1, overflow = TextOverflow.Ellipsis) },
        colors = TopAppBarDefaults.hymnsAppToolbarColors(),
        navigationIcon = {
            IconButton(onClick = { onBackPressed() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "back")
            }
        },
        actions = {
            IconButton(onClick = { operations.onFavoriteIconClicked(!isFavorite) }) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "favorite",
                )
            }
//            IconButton(onClick = { operations.onAudioIconClicked() }) {
//                Icon(
//                    imageVector = Icons.Default.PlayArrow,
//                    contentDescription = null
//                )
//            }
        }
    )
}

@Composable
fun LyricsContent(
    hymn: HymnModel,
    fontSize: Int,
) {
    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        contentAlignment = Alignment.TopCenter,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            Text("${hymn.number}. ${hymn.title}", fontSize = 22.sp)
            Text(hymn.key, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                hymn.verses.joinToString("\n\n") + "\n\n",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = fontSize.sp,
                    lineHeight = (fontSize * 1.5f).sp
                )
            )
        }
    }
}

@Composable
fun FontSizeSlider(fontSize: Int, operations: HymnDetailsOperations) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        Text(text = "Font Size", fontWeight = FontWeight.Bold, fontSize = 14.sp)

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            TextButton(onClick = { operations.onZoomDecrement() }) {
                Text(text = "A-", fontSize = 14.sp)
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                Slider(
                    value = fontSize.toFloat(),
                    onValueChange = { operations.onZoomChanged(it.toInt()) },
                    valueRange = 14f..42f,
                    steps = 14,
                )
            }
            TextButton(onClick = { operations.onZoomIncrement() }) {
                Text(text = "A+", fontSize = 24.sp)
            }
        }
    }
}
