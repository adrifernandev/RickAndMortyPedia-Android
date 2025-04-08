package com.adrifernandev.rickandmortypedia.feature.characterdetail.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.adrifernandev.rickandmortypedia.designsystem.components.icons.DSCircularPrimaryIconButton
import com.adrifernandev.rickandmortypedia.designsystem.components.image.DSAsyncImage
import com.adrifernandev.rickandmortypedia.designsystem.components.image.DSVerticalGradient
import com.adrifernandev.rickandmortypedia.designsystem.components.spacing.DSSpacer
import com.adrifernandev.rickandmortypedia.designsystem.components.spacing.SpacerType
import com.adrifernandev.rickandmortypedia.designsystem.theme.DSTheme
import com.adrifernandev.rickandmortypedia.feature.character.domain.model.CharacterStatus
import com.adrifernandev.rickandmortypedia.feature.character.presentation.ui.components.CharacterStatusIndicatorComponent
import com.adrifernandev.rickandmortypedia.feature.characterdetail.presentation.R
import com.adrifernandev.rickandmortypedia.feature.characterdetail.viewmodel.CharacterDetailViewModel
import com.adrifernandev.rickandmortypedia.common.presentation.R as R_common

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CharacterDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: CharacterDetailViewModel,
    navigateBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            DSCircularPrimaryIconButton(
                navigateBack = navigateBack,
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                iconDescription = stringResource(R_common.string.go_back_description)
            )
        },
        content = { _ ->
            CharacterDetailContent(
                modifier = modifier,
                state = state
            )
        }
    )

}

@Composable
private fun CharacterDetailContent(
    modifier: Modifier = Modifier,
    state: CharacterDetailViewModel.UIState
) {
    state.character?.let {
        Column(
            modifier = modifier.verticalScroll(rememberScrollState())
        ) {
            CharacterTopBasicInfoModule(
                characterImage = state.character.image,
                characterName = state.character.name,
                characterStatus = state.character.status,
                characterSpecies = state.character.species
            )
            CharacterLocationInfoModule(
                modifier = Modifier.padding(horizontal = DSTheme.spacing.xl),
                title = "First seen in", //TODO: Extract resource
                location = state.originLocation?.name ?: "Unknown"
            )
            DSSpacer(
                spacing = DSTheme.spacing.large,
                type = SpacerType.VERTICAL
            )
            CharacterLocationInfoModule(
                modifier = Modifier.padding(horizontal = DSTheme.spacing.xl),
                title = "Last seen in", //TODO: Extract resource
                location = state.lastKnownLocation?.name ?: "Unknown"
            )
            //TODO: Implement horizontal scroll of episodes
        }
    }
}

@Composable
private fun CharacterLocationInfoModule(
    modifier: Modifier = Modifier,
    title: String,
    location: String
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
                contentColor = MaterialTheme.colorScheme.background
            ),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = DSTheme.elevations.level5,
                pressedElevation = 0.dp,
            )
        ) {
            Box {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.rick_and_morty_planet_background),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = location,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineLarge
                )
            }
        }
    }
}

@Composable
private fun CharacterTopBasicInfoModule(
    characterImage: String,
    characterName: String,
    characterStatus: CharacterStatus,
    characterSpecies: String
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
    }
}
