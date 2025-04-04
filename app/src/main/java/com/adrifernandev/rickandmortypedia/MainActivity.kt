package com.adrifernandev.rickandmortypedia

import android.graphics.Color.TRANSPARENT
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import coil3.compose.setSingletonImageLoaderFactory
import com.adrifernandev.rickandmortypedia.common.presentation.ui.images.provideImageLoader
import com.adrifernandev.rickandmortypedia.designsystem.theme.DSTheme
import com.adrifernandev.rickandmortypedia.ui.navigation.RickAndMortyPediaApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lightTransparentStyle = SystemBarStyle.light(
            scrim = TRANSPARENT,
            darkScrim = TRANSPARENT
        )

        enableEdgeToEdge(
            statusBarStyle = lightTransparentStyle,
            navigationBarStyle = lightTransparentStyle
        )

        setContent {
            DSTheme {
                setSingletonImageLoaderFactory { context ->
                    provideImageLoader(context)
                }

                RickAndMortyPediaApp()
            }
        }
    }
}