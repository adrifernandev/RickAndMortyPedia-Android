package com.adrifernandev.rickandmortypedia.feature.home.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.adrifernandev.rickandmortypedia.common.presentation.ui.previews.PhonePreviews
import com.adrifernandev.rickandmortypedia.designsystem.components.image.DSAsyncImage
import com.adrifernandev.rickandmortypedia.designsystem.components.image.DSVerticalGradient
import com.adrifernandev.rickandmortypedia.designsystem.theme.DSTheme
import com.adrifernandev.rickandmortypedia.feature.character.domain.model.Character
import com.adrifernandev.rickandmortypedia.feature.home.presentation.R
import com.adrifernandev.rickandmortypedia.feature.home.presentation.ui.components.CharacterStatusIndicatorComponent
import com.adrifernandev.rickandmortypedia.feature.home.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    onCharacterItemClicked: (Character) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    HomeContent(
        modifier = modifier.fillMaxSize(),
        state = state,
        loadNextCharacterPage = {
            viewModel.onEvent(HomeViewModel.UIEvent.LoadNextCharacterPage)
        },
        onCharacterItemClicked = onCharacterItemClicked
    )
}

@Composable
private fun HomeContent(
    modifier: Modifier = Modifier,
    state: HomeViewModel.UIState,
    loadNextCharacterPage: () -> Unit,
    onCharacterItemClicked: (Character) -> Unit
) {
    val gridState = rememberLazyGridState()

    LazyVerticalGrid(
        modifier = modifier,
        state = gridState,
        columns = GridCells.Adaptive(minSize = 128.dp),
        contentPadding = PaddingValues(DSTheme.spacing.large),
        horizontalArrangement = Arrangement.spacedBy(DSTheme.spacing.large),
        verticalArrangement = Arrangement.spacedBy(DSTheme.spacing.large)
    ) {
        itemsIndexed(state.charactersList) { _, character ->
            ElevatedCard(
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
                    .clickable(
                        role = Role.Button,
                        onClickLabel = stringResource(
                            id = R.string.character_detail_click_description,
                            character.name
                        ),
                        onClick = { onCharacterItemClicked(character) },
                    ),
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = DSTheme.elevations.level3,
                    pressedElevation = 0.dp
                )
            ) {
                Box {
                    DSAsyncImage(
                        modifier = Modifier.fillMaxSize(),
                        imageUrl = character.image,
                        contentDescription = stringResource(
                            id = R.string.avatar_image_description,
                            character.name
                        )
                    )
                    DSVerticalGradient(endY = 400f)
                    Row(modifier = Modifier.align(Alignment.TopStart)) {
                        CharacterStatusIndicatorComponent(status = character.status)
                    }
                    Row(modifier = Modifier.align(Alignment.BottomStart)) {
                        Text(
                            modifier = Modifier.padding(DSTheme.spacing.medium),
                            text = character.name,
                            style = MaterialTheme.typography.headlineSmall,
                            maxLines = 2,
                            color = Color.White,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Start
                        )
                    }
                }
            }
        }
    }

    LaunchedEffect(gridState) {
        snapshotFlow { gridState.layoutInfo.visibleItemsInfo }
            .collect { visibleItems ->
                if (visibleItems.isNotEmpty()) {
                    val lastItem = visibleItems.last()
                    if (lastItem.index >= state.charactersList.size - 5) {
                        loadNextCharacterPage()
                    }
                }
            }
    }
}

@PhonePreviews
@Composable
private fun HomeContentPreview() {
    DSTheme {
        HomeContent(
            state = HomeViewModel.UIState(),
            onCharacterItemClicked = {},
            loadNextCharacterPage = {}
        )
    }
}