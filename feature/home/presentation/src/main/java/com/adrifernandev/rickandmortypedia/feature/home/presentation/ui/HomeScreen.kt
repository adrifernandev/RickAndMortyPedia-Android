package com.adrifernandev.rickandmortypedia.feature.home.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adrifernandev.rickandmortypedia.common.presentation.ui.previews.PhonePreviews
import com.adrifernandev.rickandmortypedia.designsystem.theme.DSTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    HomeContent(
        modifier = modifier.fillMaxSize()
    )
}

@Composable
private fun HomeContent(
    modifier: Modifier = Modifier
) {
    Text("Hello world! I'm home screen!")
}

@PhonePreviews
@Composable
private fun HomeContentPreview() {
    DSTheme {
        HomeContent()
    }
}