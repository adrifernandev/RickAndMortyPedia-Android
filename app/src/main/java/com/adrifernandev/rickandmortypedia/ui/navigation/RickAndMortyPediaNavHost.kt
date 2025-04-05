package com.adrifernandev.rickandmortypedia.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.adrifernandev.rickandmortypedia.feature.character.domain.model.Character
import com.adrifernandev.rickandmortypedia.feature.characterdetail.ui.CharacterDetailScreen
import com.adrifernandev.rickandmortypedia.feature.characterdetail.viewmodel.CharacterDetailViewModel
import com.adrifernandev.rickandmortypedia.feature.home.presentation.ui.HomeScreen
import com.adrifernandev.rickandmortypedia.feature.home.presentation.viewmodel.HomeViewModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RickAndMortyPediaNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel(),
    characterViewModel: CharacterDetailViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background
    ) { _ ->
        NavHost(
            modifier = Modifier,
            navController = navHostController,
            startDestination = NavigationRoutes.HomeGraph
        ) {

            //Home Graph
            navigation<NavigationRoutes.HomeGraph>(
                startDestination = NavigationRoutes.Home
            ) {
                composable<NavigationRoutes.Home> {
                    HomeScreen(viewModel = homeViewModel) { character ->
                        val characterToJson = Json.encodeToString(character)
                        navHostController.navigate(
                            NavigationRoutes.CharacterDetail(
                                characterToJson
                            )
                        )
                    }
                }
            }

            navigation<NavigationRoutes.CharacterGraph>(
                startDestination = NavigationRoutes.CharacterDetail("")
            ) {
                composable<NavigationRoutes.CharacterDetail> {
                    val argument = it.toRoute<NavigationRoutes.CharacterDetail>()
                    val character = Json.decodeFromString<Character>(argument.character)

                    characterViewModel.onEvent(
                        CharacterDetailViewModel.UIEvent.InitializeCharacter(
                            character
                        )
                    )

                    CharacterDetailScreen(viewModel = characterViewModel)
                }
            }
        }
    }
}