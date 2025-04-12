package com.adrifernandev.rickandmortypedia.designsystem.components.topbars

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.adrifernandev.rickandmortypedia.designsystem.theme.DSTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DSTextAndLogoTopBar(
    @StringRes text: Int,
    @DrawableRes logo: Int
) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(DSTheme.spacing.large)
            ) {
                Image(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(id = logo),
                    contentDescription = null
                )
                Text(
                    text = stringResource(text)
                )
            }
        }
    )
}