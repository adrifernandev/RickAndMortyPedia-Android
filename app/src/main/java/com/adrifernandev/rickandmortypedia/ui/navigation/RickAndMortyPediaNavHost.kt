package com.adrifernandev.rickandmortypedia.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.adrifernandev.rickandmortypedia.feature.home.presentation.ui.HomeScreen
import com.adrifernandev.rickandmortypedia.feature.home.presentation.viewmodel.HomeViewModel

@Composable
fun RickAndMortyPediaNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background
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
                    HomeScreen(viewModel = homeViewModel)
                }
            }
        }
    }
}