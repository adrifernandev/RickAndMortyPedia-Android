package com.adrifernandev.rickandmortypedia.designsystem.components.icons

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.adrifernandev.rickandmortypedia.designsystem.theme.DSTheme

@Composable
fun DSCircularPrimaryIconButton(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    icon: ImageVector,
    iconDescription: String
) {
    IconButton(
        modifier = modifier.padding(DSTheme.spacing.xl),
        colors = IconButtonDefaults.filledIconButtonColors(
            containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
            contentColor = MaterialTheme.colorScheme.primaryContainer
        ),
        onClick = navigateBack
    ) {
        Icon(
            imageVector = icon,
            contentDescription = iconDescription
        )
    }
}