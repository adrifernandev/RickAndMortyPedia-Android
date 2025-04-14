package com.adrifernandev.rickandmortypedia.feature.characterdetail.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.adrifernandev.rickandmortypedia.common.presentation.ui.dates.DatesFormatter.formatDateToString
import com.adrifernandev.rickandmortypedia.common.presentation.ui.events.ObserveAsEvents
import com.adrifernandev.rickandmortypedia.designsystem.components.icons.DSCircularPrimaryIconButton
import com.adrifernandev.rickandmortypedia.designsystem.components.image.DSAsyncImage
import com.adrifernandev.rickandmortypedia.designsystem.components.image.DSVerticalGradient
import com.adrifernandev.rickandmortypedia.designsystem.components.spacing.DSSpacer
import com.adrifernandev.rickandmortypedia.designsystem.components.spacing.SpacerType
import com.adrifernandev.rickandmortypedia.designsystem.theme.DSTheme
import com.adrifernandev.rickandmortypedia.episode.domain.model.Episode
import com.adrifernandev.rickandmortypedia.feature.character.domain.model.CharacterStatus
import com.adrifernandev.rickandmortypedia.feature.character.presentation.ui.components.CharacterStatusIndicatorComponent
import com.adrifernandev.rickandmortypedia.feature.characterdetail.presentation.R
import com.adrifernandev.rickandmortypedia.feature.characterdetail.viewmodel.CharacterDetailViewModel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import com.adrifernandev.rickandmortypedia.common.presentation.R as R_common

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CharacterDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: CharacterDetailViewModel,
    snackbarHostState: SnackbarHostState,
    navigateBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ProcessActions(
        uiAction = viewModel.uiAction,
        snackbarHostState = snackbarHostState
    )

    CharacterDetailContent(
        modifier = modifier,
        state = state,
        navigateBack = navigateBack
    )
}

@Composable
private fun CharacterDetailContent(
    modifier: Modifier = Modifier,
    state: CharacterDetailViewModel.UIState,
    navigateBack: () -> Unit
) {
    state.character?.let {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            CharacterTopBasicInfoModule(
                characterImage = state.character.image,
                characterName = state.character.name,
                characterStatus = state.character.status,
                characterSpecies = state.character.species,
                navigateBack = navigateBack
            )
            CharacterLocationInfoModule(
                modifier = Modifier.padding(horizontal = DSTheme.spacing.xl),
                title = stringResource(R.string.origin_location),
                location = state.originLocation?.name ?: stringResource(R.string.unknown),
                dimension = state.originLocation?.dimension
            )
            DSSpacer(
                spacing = DSTheme.spacing.large,
                type = SpacerType.VERTICAL
            )
            CharacterLocationInfoModule(
                modifier = Modifier.padding(horizontal = DSTheme.spacing.xl),
                title = stringResource(R.string.last_known_location),
                location = state.lastKnownLocation?.name ?: stringResource(R.string.unknown),
                dimension = state.originLocation?.dimension
            )
            DSSpacer(
                spacing = DSTheme.spacing.large,
                type = SpacerType.VERTICAL
            )
            state.episodes?.let {
                CharacterEpisodesModule(
                    episodes = it
                )
            }
            DSSpacer(
                spacing = DSTheme.spacing.xxxxl,
                type = SpacerType.VERTICAL
            )
        }
    }
}

@Composable
private fun CharacterEpisodesModule(
    episodes: List<Episode>
) {
    Text(
        modifier = Modifier.padding(horizontal = DSTheme.spacing.xl),
        text = pluralStringResource(
            id = R.plurals.seen_on_episodes,
            count = episodes.size
        ),
        style = MaterialTheme.typography.bodyLarge
    )
    DSSpacer(
        spacing = DSTheme.spacing.medium,
        type = SpacerType.VERTICAL
    )
    LazyRow(
        contentPadding = PaddingValues(horizontal = DSTheme.spacing.xl),
        horizontalArrangement = Arrangement.spacedBy(DSTheme.spacing.large)
    ) {
        itemsIndexed(episodes) { _, episode ->
            ElevatedCard(
                modifier = Modifier.size(200.dp),
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = DSTheme.elevations.level5,
                    pressedElevation = 0.dp,
                ),
                colors = CardDefaults.elevatedCardColors(
                    contentColor = Color.Black
                )
            ) {
                Box {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(R.drawable.rick_and_morty_gray_background),
                        contentScale = ContentScale.Crop,
                        contentDescription = null
                    )
                    Text(
                        modifier = Modifier
                            .padding(DSTheme.spacing.medium)
                            .align(Alignment.TopStart),
                        text = stringResource(
                            id = R.string.season_and_episode,
                            episode.season,
                            episode.episode
                        ),
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Column(
                        modifier = Modifier
                            .padding(DSTheme.spacing.medium)
                            .align(Alignment.BottomStart)
                    ) {
                        Text(
                            text = episode.name,
                            style = MaterialTheme.typography.headlineSmall,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Start
                        )
                        episode.airDate?.let {
                            Text(
                                text = formatDateToString(date = it),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CharacterLocationInfoModule(
    modifier: Modifier = Modifier,
    title: String,
    location: String,
    dimension: String? = null
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge
        )
        DSSpacer(
            spacing = DSTheme.spacing.medium,
            type = SpacerType.VERTICAL
        )
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            colors = CardDefaults.elevatedCardColors(
                contentColor = Color.White
            ),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = DSTheme.elevations.level5,
                pressedElevation = 0.dp,
            )
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.rick_and_morty_planet_background),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
                Column(
                    modifier = Modifier.padding(DSTheme.spacing.medium),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = location,
                        textAlign = TextAlign.Center,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.headlineLarge
                    )
                    dimension?.let {
                        Text(
                            text = dimension,
                            textAlign = TextAlign.Center,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }

            }
        }
    }
}

@Composable
private fun CharacterTopBasicInfoModule(
    characterImage: String,
    characterName: String,
    characterStatus: CharacterStatus,
    characterSpecies: String,
    navigateBack: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(450.dp)
    ) {
        DSAsyncImage(
            imageUrl = characterImage,
            contentDescription = stringResource(
                id = R_common.string.avatar_image_description,
                characterName
            )
        )
        DSVerticalGradient(
            endY = 1150f,
            colors = listOf(
                Color.Transparent,
                MaterialTheme.colorScheme.background
            )
        )
        Column(
            Modifier
                .align(Alignment.BottomStart)
                .padding(horizontal = DSTheme.spacing.xl)
                .padding(bottom = DSTheme.spacing.xl)
        ) {
            Text(
                text = characterName,
                style = MaterialTheme.typography.displayMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onBackground
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                CharacterStatusIndicatorComponent(
                    modifier = Modifier.size(32.dp),
                    status = characterStatus
                )
                Text(
                    text = "${characterStatus.name} - $characterSpecies",
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
        DSCircularPrimaryIconButton(
            modifier = Modifier.align(Alignment.TopStart),
            navigateBack = navigateBack,
            icon = Icons.AutoMirrored.Filled.ArrowBack,
            iconDescription = stringResource(R_common.string.go_back_description)
        )
    }
}

@Composable
private fun ProcessActions(
    uiAction: SharedFlow<CharacterDetailViewModel.UIAction>,
    snackbarHostState: SnackbarHostState
) {
    val coroutineScope = rememberCoroutineScope()
    val errorMessage = stringResource(R_common.string.generic_error_message)
    ObserveAsEvents(uiAction) { action ->
        when (action) {
            is CharacterDetailViewModel.UIAction.ShowObtainingDataError -> {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        message = errorMessage,
                        withDismissAction = true,
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }
    }
}