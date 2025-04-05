package com.adrifernandev.rickandmortypedia.feature.character.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.adrifernandev.rickandmortypedia.designsystem.theme.DSTheme
import com.adrifernandev.rickandmortypedia.feature.character.domain.model.CharacterStatus

private val aliveColor = Color(0xFF97CE4C)
private val deadColor = Color(0xFF780606)
private val unknownColor = Color(0xFF757575)

@Composable
fun CharacterStatusIndicatorComponent(
    modifier: Modifier = Modifier,
    status: CharacterStatus,
) {
    Box(
        modifier = modifier
            .padding(DSTheme.spacing.medium)
            .clip(CircleShape)
            .size(24.dp)
            .background(getStatusColor(status))
    )
}

private fun getStatusColor(
    status: CharacterStatus
): Color {
    return when (status) {
        CharacterStatus.Alive -> aliveColor
        CharacterStatus.Dead -> deadColor
        else -> unknownColor
    }
}