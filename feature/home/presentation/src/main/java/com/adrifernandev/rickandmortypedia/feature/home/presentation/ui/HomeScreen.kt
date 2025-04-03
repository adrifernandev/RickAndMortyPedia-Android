package com.adrifernandev.rickandmortypedia.feature.home.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.adrifernandev.rickandmortypedia.common.presentation.ui.previews.PhonePreviews
import com.adrifernandev.rickandmortypedia.designsystem.theme.DSTheme
import com.adrifernandev.rickandmortypedia.feature.home.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    HomeContent(
        modifier = modifier.fillMaxSize(),
        state = state
    )
}

@Composable
private fun HomeContent(
    modifier: Modifier = Modifier,
    state: HomeViewModel.UIState
) {
    Text("Hello world! I'm home screen! ${state.characters?.first()?.name}")
}

@PhonePreviews
@Composable
private fun HomeContentPreview() {
    DSTheme {
        HomeContent(
            state = HomeViewModel.UIState(
                characters = null
            )
        )
    }
}