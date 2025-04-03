package com.adrifernandev.rickandmortypedia.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

@Composable
fun RickAndMortyPediaApp() {
    val navHostController = rememberNavController()

    RickAndMortyPediaNavHost(
        navHostController = navHostController
    )
}