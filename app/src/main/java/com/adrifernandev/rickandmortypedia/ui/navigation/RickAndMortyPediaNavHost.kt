package com.adrifernandev.rickandmortypedia.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.adrifernandev.rickandmortypedia.common.presentation.R
import com.adrifernandev.rickandmortypedia.common.presentation.ui.navigation.getEnterTransition
import com.adrifernandev.rickandmortypedia.common.presentation.ui.navigation.getExitTransition
import com.adrifernandev.rickandmortypedia.designsystem.components.topbars.DSTextAndLogoTopBar
import com.adrifernandev.rickandmortypedia.feature.character.domain.model.Character
import com.adrifernandev.rickandmortypedia.feature.characterdetail.ui.CharacterDetailScreen
import com.adrifernandev.rickandmortypedia.feature.characterdetail.viewmodel.CharacterDetailViewModel
import com.adrifernandev.rickandmortypedia.feature.home.presentation.ui.HomeScreen
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RickAndMortyPediaNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    val snackbarHostState = remember { SnackbarHostState() }
    var currentNavigationRoute by remember { mutableStateOf<NavigationRoutes?>(null) }

    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            if (currentNavigationRoute?.isTopBarShown == true) {
                DSTextAndLogoTopBar(
                    text = R.string.app_name
                )
            }
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { paddingValues ->
        val topPadding = if (currentNavigationRoute?.isTopBarShown == true) {
            paddingValues.calculateTopPadding()
        } else {
            0.dp
        }

        NavHost(
            modifier = Modifier
                .padding(top = topPadding),
            navController = navHostController,
            startDestination = NavigationRoutes.HomeGraph
        ) {

            //Home Graph
            navigation<NavigationRoutes.HomeGraph>(
                startDestination = NavigationRoutes.Home
            ) {
                composable<NavigationRoutes.Home>(
                    enterTransition = getEnterTransition(direction = AnimatedContentTransitionScope.SlideDirection.Right),
                    exitTransition = getExitTransition(direction = AnimatedContentTransitionScope.SlideDirection.Left)
                ) {
                    currentNavigationRoute = NavigationRoutes.Home
                    HomeScreen(
                        viewModel = hiltViewModel(),
                        snackbarHostState = snackbarHostState
                    ) { character ->
                        val characterToJson = Json.encodeToString(character)
                        navHostController.navigate(
                            NavigationRoutes.CharacterDetail(
                                characterToJson
                            )
                        ) {
                            launchSingleTop = true
                        }
                    }
                }
            }

            navigation<NavigationRoutes.CharacterGraph>(
                startDestination = NavigationRoutes.CharacterDetail("")
            ) {
                composable<NavigationRoutes.CharacterDetail>(
                    enterTransition = getEnterTransition(direction = AnimatedContentTransitionScope.SlideDirection.Left),
                    exitTransition = getExitTransition(direction = AnimatedContentTransitionScope.SlideDirection.Right)
                ) {
                    val argument = it.toRoute<NavigationRoutes.CharacterDetail>()
                    val character = Json.decodeFromString<Character>(argument.character)

                    val characterViewModel: CharacterDetailViewModel = hiltViewModel()

                    characterViewModel.onEvent(
                        CharacterDetailViewModel.UIEvent.InitializeCharacter(
                            character
                        )
                    )
                    currentNavigationRoute = NavigationRoutes.CharacterDetail("")

                    CharacterDetailScreen(
                        viewModel = characterViewModel,
                        snackbarHostState = snackbarHostState
                    ) {
                        navHostController.navigateUp()
                    }
                }
            }
        }
    }
}