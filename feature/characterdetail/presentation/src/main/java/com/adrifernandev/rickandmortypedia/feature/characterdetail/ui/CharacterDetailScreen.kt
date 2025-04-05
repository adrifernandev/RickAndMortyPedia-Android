package com.adrifernandev.rickandmortypedia.feature.characterdetail.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.adrifernandev.rickandmortypedia.designsystem.components.image.DSAsyncImage
import com.adrifernandev.rickandmortypedia.designsystem.components.image.DSVerticalGradient
import com.adrifernandev.rickandmortypedia.feature.characterdetail.viewmodel.CharacterDetailViewModel
import com.adrifernandev.rickandmortypedia.common.presentation.R as R_common

@Composable
fun CharacterDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: CharacterDetailViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    CharacterDetailContent(
        modifier = modifier,
        state = state
    )
}

@Composable
private fun CharacterDetailContent(
    modifier: Modifier = Modifier,
    state: CharacterDetailViewModel.UIState
) {
    state.character?.let {
        Column(
            modifier = modifier
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp)
            ) {
                DSAsyncImage(
                    imageUrl = state.character.image,
                    contentDescription = stringResource(
                        id = R_common.string.avatar_image_description,
                        state.character.name
                    )
                )
                DSVerticalGradient(
                    endY = 1000f,
                    colors = listOf(
                        Color.Transparent,
                        MaterialTheme.colorScheme.background
                    )
                )
            }

        }
    }
}

