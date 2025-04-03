package com.adrifernandev.rickandmortypedia.ui.navigation

import kotlinx.serialization.Serializable

sealed class NavigationRoutes {

    //Home Graph
    @Serializable
    data object HomeGraph : NavigationRoutes()

    @Serializable
    data object Home : NavigationRoutes()
}