package com.adrifernandev.rickandmortypedia.designsystem.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// Primary brand colors
private val green = Color(0xFF97CE4C)
private val brown = Color(0xFF44281D)
private val pink = Color(0xFFE89AC7)

// Neutral colors
private val white = Color(0xFFFFFFFF)
private val black = Color(0xFF000000)
private val grey900 = Color(0xFF212121)
private val grey700 = Color(0xFF757575)
private val grey400 = Color(0xFFBDBDBD)
private val grey200 = Color(0xFFE0E0E0)

val dsDarkColorScheme = darkColorScheme(
    primary = green,
    onPrimary = black,
    primaryContainer = black,
    onPrimaryContainer = white,
    secondary = pink,
    onSecondary = black,
    background = black,
    onBackground = grey400,
    surface = brown,
    onSurface = grey200,
    onSurfaceVariant = grey700,
    outline = grey400
)

val dsLightColorScheme = lightColorScheme(
    primary = green,
    onPrimary = black,
    primaryContainer = white,
    onPrimaryContainer = black,
    secondary = pink,
    onSecondary = black,
    background = white,
    onBackground = grey900,
    surface = white,
    onSurface = grey900,
    onSurfaceVariant = grey700,
    outline = grey400
)