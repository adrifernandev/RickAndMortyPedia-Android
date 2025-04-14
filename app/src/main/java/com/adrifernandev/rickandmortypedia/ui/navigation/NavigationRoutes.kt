package com.adrifernandev.rickandmortypedia.ui.navigation

import kotlinx.serialization.Serializable

sealed class NavigationRoutes {

    open val isTopBarShown = false

    //Home Graph
    @Serializable
    data object HomeGraph : NavigationRoutes()

    @Serializable
    data object Home : NavigationRoutes() {
        override val isTopBarShown: Boolean
            get() = true
    }

    //Character Graph
    @Serializable
    data object CharacterGraph : NavigationRoutes()

    @Serializable
    data class CharacterDetail(
        val character: String
    ) : NavigationRoutes()
}