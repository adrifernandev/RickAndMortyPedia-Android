package com.adrifernandev.rickandmortypedia.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation

@Composable
fun RickAndMortyPediaNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    Scaffold(
        modifier = modifier
    ) { contentPadding ->
        NavHost(
            modifier = Modifier.padding(contentPadding),
            navController = navHostController,
            startDestination = NavigationRoutes.HomeGraph
        ) {

            //Home Graph
            navigation<NavigationRoutes.HomeGraph>(
                startDestination = NavigationRoutes.Home
            ) {
                composable<NavigationRoutes.Home> {
                    //TODO: Implement screen
                }
            }
        }
    }
}