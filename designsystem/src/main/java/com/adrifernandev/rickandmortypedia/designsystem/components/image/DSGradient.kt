package com.adrifernandev.rickandmortypedia.designsystem.components.image

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

private val defaultGradientColors = listOf(
    Color.Transparent,
    Color.Black.copy(alpha = 0.8f)
)

@Composable
fun DSVerticalGradient(
    modifier: Modifier = Modifier,
    colors: List<Color> = defaultGradientColors,
    startY: Float = 0f,
    endY: Float
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = colors,
                    startY = startY,
                    endY = endY
                )
            )
    )
}