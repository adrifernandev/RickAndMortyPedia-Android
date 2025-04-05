package com.adrifernandev.rickandmortypedia.feature.characterdetail.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.adrifernandev.rickandmortypedia.designsystem.components.icons.DSCircularPrimaryIconButton
import com.adrifernandev.rickandmortypedia.designsystem.components.image.DSAsyncImage
import com.adrifernandev.rickandmortypedia.designsystem.components.image.DSVerticalGradient
import com.adrifernandev.rickandmortypedia.designsystem.theme.DSTheme
import com.adrifernandev.rickandmortypedia.feature.character.domain.model.CharacterStatus
import com.adrifernandev.rickandmortypedia.feature.character.presentation.ui.components.CharacterStatusIndicatorComponent
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
            //TODO: Implement origin of the character
            //TODO: Implement last known location of the character
            //TODO: Implement horizontal scroll of episodes
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
